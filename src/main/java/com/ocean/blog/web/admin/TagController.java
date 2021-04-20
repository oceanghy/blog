package com.ocean.blog.web.admin;

import com.ocean.blog.po.Tag;
import com.ocean.blog.po.Type;
import com.ocean.blog.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    private TagsService tagsService;

    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 7,sort = {"id"},direction = Sort.Direction.DESC)
                                   Pageable pageable, Model model){
        model.addAttribute("page",tagsService.listTag(pageable));
        return "admin/tags";
    }
    @GetMapping("/tags/input")//确定页面到的跳转
    public String input(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tags-input";
    }

    //编辑
    @GetMapping("tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("tag",tagsService.getTag(id));
        return "admin/tags-input";
    }

    @PostMapping("/tags")//两个不同的请求方式访问同一个界面不会冲突
    public String post(@Valid Tag tag, BindingResult result, RedirectAttributes attributes){
        Tag t2 = tagsService.getTypeByName(tag.getName());
        if (t2!=null){
            result.rejectValue("name","nameError","该标签已经存在");
        }
        if (result.hasErrors()){
            return "admin/tags-input";
        }
        Tag t = tagsService.saveTag(tag);//将表单中name封装到type中
        if (t==null){
            attributes.addFlashAttribute("message","操作失败");
        }else {
            attributes.addFlashAttribute("message","操作成功");
        }
        return "redirect:/admin/tags";//注意使用重定向
    }

    @PostMapping("/tags/{id}")//两个不同的请求方式访问同一个界面不会冲突
    public String editPost(@Valid Tag tag, BindingResult result,@PathVariable Long id, RedirectAttributes attributes){
        Tag t3 = tagsService.getTypeByName(tag.getName());
        if (t3!=null){
            result.rejectValue("name","nameError","该标签已经存在");
        }
        if (result.hasErrors()){
            return "admin/tags-input";
        }
        Tag t = tagsService.saveTag(tag);//将表单中name封装到type中
        if (t==null){
            attributes.addFlashAttribute("message","操作失败");
        }else {
            attributes.addFlashAttribute("message","操作成功");
        }
        return "redirect:/admin/tags";//注意使用重定向
    }
    //删除
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        tagsService.deleteTag(id);
        attributes.addFlashAttribute("message","删除成功！");
        return "redirect:/admin/tags";
    }
}

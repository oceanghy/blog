package com.ocean.blog.web.admin;

import com.ocean.blog.po.Type;
import com.ocean.blog.service.TypeService;
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

/*
    这是类型查询的类
 */
@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    private TypeService typeService;
    @GetMapping("/types")
    //分页查询
    public String types(@PageableDefault(size = 7,sort = {"id"},direction = Sort.Direction.DESC)
                                    Pageable pageable,Model model){//需要设置分页的参数,以及后端返回的model
        model.addAttribute("page",typeService.listType(pageable));//将获取的page传输到model中
        return "admin/types";
    }

    @GetMapping("/types/input")//确定页面到的跳转
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    //编辑
    @GetMapping("types/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";
    }

    @PostMapping("/types")//两个不同的请求方式访问同一个界面不会冲突
    public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes){
        Type t2 = typeService.getTypeByName(type.getName());
        if (t2!=null){
            result.rejectValue("name","nameError","该类别已经存在");
        }
        if (result.hasErrors()){
            return "admin/types-input";
        }
        Type t = typeService.saveType(type);//将表单中name封装到type中
        if (t==null){
            attributes.addFlashAttribute("message","操作失败");
        }else {
            attributes.addFlashAttribute("message","操作成功");
        }
        return "redirect:/admin/types";//注意使用重定向
    }

    @PostMapping("/types/{id}")//两个不同的请求方式访问同一个界面不会冲突
    public String editPost(@Valid Type type, BindingResult result,@PathVariable Long id, RedirectAttributes attributes){
        Type t3 = typeService.getTypeByName(type.getName());
        if (t3!=null){
            result.rejectValue("name","nameError","该类别已经存在");
        }
        if (result.hasErrors()){
            return "admin/types-input";
        }
        Type t = typeService.updateType(id,type);//将表单中name封装到type中
        if (t==null){
            attributes.addFlashAttribute("message","操作失败");
        }else {
            attributes.addFlashAttribute("message","操作成功");
        }
        return "redirect:/admin/types";//注意使用重定向
    }
    //删除
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功！");
        return "redirect:/admin/types";
    }

}

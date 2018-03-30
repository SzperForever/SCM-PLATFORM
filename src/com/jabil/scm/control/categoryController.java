package com.jabil.scm.control;

import com.jabil.scm.model.Category;
import com.jabil.scm.service.categoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class categoryController {
    private categoryService categoryService = new categoryService();


    /**
     *
     * @return 返回所有的类别
     */
    @RequestMapping(value = "/getCategory")
    @ResponseBody
    public ArrayList<Category> getCats() {
        return categoryService.getCategory();
    }

    @RequestMapping(value="insertCats")
    @ResponseBody
    public ArrayList<Category> insertCats(){
        Category category = new Category(6, "Shenanyi");
        categoryService.insertCategory(category);
        return categoryService.getCategory();
    }


}

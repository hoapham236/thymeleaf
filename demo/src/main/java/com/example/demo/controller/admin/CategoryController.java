package com.example.demo.controller.admin;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute; 
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation. PostMapping;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.CategoryEntity;
import com.example.demo.service.ICategoryService;

@Controller
@RequestMapping("admin/categories")
public class CategoryController {
	@Autowired
	ICategoryService categoryService;
//	@GetMapping("add")
//	public String add (ModelMap model) {
//		CategoryModel cateModel = new Category Model(); 
//		cateModel.setIsEdit(false);
//		// chuyển dữ liệu từ model vào biến category để đưa lên view 
//		model.addAttribute("category", cateModel); 
//		return "admin/categories/addOrEdit";
//	}
	@GetMapping("/add") 
	public String add(ModelMap model) { 
		
		 return"admin/add";
		  
	}
	@PostMapping("save")
	public String add(@Valid CategoryEntity category, BindingResult result, Model model){
        if (result.hasErrors()) {
                return "admin/categories/add";
        }
        categoryService.save(category);
        return "redirect:/admin/categories";
	}
	 @GetMapping("/edit/{id}")
     public String edit(@PathVariable("id") long id, Model model){
             CategoryEntity category = categoryService.findById(id)
                     .orElseThrow(() -> new RuntimeException("Not found"));
             model.addAttribute("category", category);
             return "CRUD/category_update";
     }

	@RequestMapping("")
	public String list(ModelMap model){
		//gọi hàm findAll() trong service
		List<CategoryEntity> list = categoryService.findAll();
		// chuyển dữ liệu từ list lên biến categories
		model.addAttribute("categories", list);
		return "admin/categories/list";
	}

//	@GetMapping("delete/{categoryId}")
//	public ModelAndView delet (ModelMap model, @PathVariable("categoryId") Long categoryId) {
//		categoryService.deleteById(categoryId);
//		model.addAttribute("message", "Category is deleted!!!!");
//		return new ModelAndView("forward:/admin/categories/searchpaginated", model);
//	}
//	@GetMapping("search")
//	public String search (ModelMap model, @RequestParam(name="name", required = false) String name) {
//		List<CategoryEntity> list = null;
//		// có nội dung truyền về không, name là tùy chọn khi required=false
//		if(StringUtils.hasText(name)) {
//		list = categoryService.findByNameContaining(name);
//		}else {
//		list = categoryService.findAll();
//		}
//		model.addAttribute("categories", list);
//		return "admin/categories/search";
//	}
//	@RequestMapping("searchpaginated")
//	public String search (ModelMap model,
//			@RequestParam(name="name", required = false) String name,
//			@RequestParam("page") Optional<Integer> page,
//			@RequestParam("size") Optional<Integer> size) {
//		int count = (int) categoryService.count();
//		int currentPage= page.orElse(1);
//		int pageSize = size.orElse(3);
//		Pageable pageable = PageRequest.of(currentPage-1, pageSize, Sort.by("name"));
//		Page<CategoryEntity> resultPage= null;
//		if(StringUtils.hasText(name)) { resultPage = categoryService.findByNameContaining(name, pageable);
//			model.addAttribute("name", name);
//		}else { resultPage = categoryService.findAll(pageable);
//		}
//		int totalPages = resultPage.getTotalPages();
//		if(totalPages > 0) {
//			int start = Math.max(1, currentPage-2);
//			int end = Math.min(currentPage + 2, totalPages);
//			if(totalPages > count) {
//					if(end == totalPages) start = end - count;
//					else if (start == 1) end = start + count;
//			}
//			List<Integer> pageNumbers = IntStream.rangeClosed(start, end) .boxed().collect(Collectors.toList()); 
//			model.addAttribute("pageNumbers", pageNumbers); 
//			
//		} model.addAttribute("categoryPage", resultPage);
//		return "admin/categories/searchpaginated";
//	}
}

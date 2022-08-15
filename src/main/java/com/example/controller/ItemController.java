package com.example.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Conventions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.entity.Item;
import com.example.form.ItemForm;
import com.example.pagination.Pagination;
import com.example.service.CategoryService;
import com.example.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	@ModelAttribute
	public Pagination setUpToPagination() {
		return new Pagination();
	}
	
	@ModelAttribute
	public ItemForm setUpToItemForm() {
		return new ItemForm();
	}
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private CategoryService categoryService;
	
	/** item全件リストを表示
	 * @param pagination
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String toShowItemList(Pagination pagination, Model model) {
		List<Item> itemList = itemService.getAllItems(pagination);
		pagination = itemService.paging(pagination);
		
		model.addAttribute("itemList", itemList);
		model.addAttribute("pagination", pagination);
		
		return "list";
	}
	

	
	/**
	 * item詳細リストを表示
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/detail")
	public String toShowItemDetail(@RequestParam("id") String id, Model model) {
		Item item = itemService.getDetail(Integer.parseInt(id));
		model.addAttribute("item", item);
		return "detail";
	}
	

	/**
	 * item新規追加画面へ遷移
	 * @return
	 */
	@RequestMapping("/add")
	public String toShowItemAddForm(Model model) {
		Set<String> parentCategoryList = categoryService.getParentCategoryList();
		model.addAttribute("parentCategoryList", parentCategoryList);
		return "add";
	}
	
	/**
	 * item新規追加
	 * @return
	 */
	@RequestMapping("/add/comp")
	public String itemAdd(@Validated ItemForm itemForm, BindingResult result, RedirectAttributes redirectAttribute) {
		if(result.hasErrors()) {
			redirectAttribute.addFlashAttribute(itemForm);
			redirectAttribute.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + Conventions.getVariableName(itemForm), result);
			return "redirect:/item/add";
		}
		
		itemForm.setCategory(itemForm.getParentCategory(), itemForm.getChildCategory(), itemForm.getGrandChild());
		Item item = new Item();
		BeanUtils.copyProperties(itemForm, item);
		item.setPrice(Double.parseDouble(itemForm.getPrice()));
		
		itemService.register(item);
		
		return "redirect:/item/list";
	}

	/**
	 * item情報編集画面遷移
	 * @param id
	 * @return
	 */
	@RequestMapping("/edit")
	public String toShowEditForm(@RequestParam("id") String id, Model model) {
		Item item = itemService.getDetail(Integer.parseInt(id));
		model.addAttribute("item", item);
		
		String[] categoryArray = item.getCategory().split("/");
		
		// parentCategory
		String originalParentCategory = categoryArray[0];
		Set<String> parentCategoryList = categoryService.getParentCategoryList();
		model.addAttribute("originalParentCategory", originalParentCategory);
		model.addAttribute("parentCategoryList", parentCategoryList);
		
		// childCategory
		String originalChildCategory = categoryArray[1];
		Set<String> childCategoryList = categoryService.getChildCategoryList(originalParentCategory);
		model.addAttribute("originalChildCategory", originalChildCategory);
		model.addAttribute("childCategoryList", childCategoryList);
		
		// grandCategory
		String originalGrandCategory = categoryArray[2];
		Set<String> grandCategoryList = categoryService.getGrandCategoryList(originalChildCategory);
		model.addAttribute("originalGrandCategory", originalGrandCategory);
		model.addAttribute("grandCategoryList", grandCategoryList);
		
		return "edit";
	}
	
	/**
	 * itemを編集する
	 * @param itemForm
	 * @param result
	 * @param redirectAttribute
	 * @return
	 */
	@RequestMapping("/edit/comp")
	public String itemEdit(@Validated ItemForm itemForm, BindingResult result, RedirectAttributes redirectAttribute) {
		if(result.hasErrors()) {
			redirectAttribute.addFlashAttribute(itemForm);
			redirectAttribute.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + Conventions.getVariableName(itemForm), result);
			redirectAttribute.addAttribute("id", itemForm.getId());
			return "redirect:/item/edit";
		}
		
		itemForm.setCategory(itemForm.getParentCategory(), itemForm.getChildCategory(), itemForm.getGrandChild());
		Item item = new Item();
		BeanUtils.copyProperties(itemForm, item);
		item.setPrice(Double.parseDouble(itemForm.getPrice()));
		
		itemService.edit(item);
		
		redirectAttribute.addAttribute("id", itemForm.getId());
		return "redirect:/item/detail";
	}

}

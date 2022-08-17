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
import com.example.entity.ItemSearch;
import com.example.form.ItemEditForm;
import com.example.form.ItemAddForm;
import com.example.form.ItemSearchForm;
import com.example.pagination.Pagination;
import com.example.service.CategoryService;
import com.example.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	/**	ページング処理用のオブジェクト作成 **/
	@ModelAttribute
	public Pagination setUpToPagination() {
		return new Pagination();
	}
	
	@ModelAttribute
	public ItemAddForm setUpToItemAddForm() {
		return new ItemAddForm();
	}
	
	@ModelAttribute
	public ItemEditForm setUpToItemEditForm() {
		return new ItemEditForm();
	}
	
	@ModelAttribute
	public ItemSearchForm setUpToItemSearchForm() {
		return new ItemSearchForm();
	}
	
	/**	親カテゴリ検索時のプルダウン表示用 **/
	@ModelAttribute(name = "parentCategorySearchList")
	public Set<String> setParentcategory(){
		return categoryService.getParentCategory();
	}
	
	/**	子カテゴリ検索時のプルダウン表示用 **/
	@ModelAttribute(name = "childCategorySearchList")
	public Set<String> setChildcategory(){
		return categoryService.getChildCategory();
	}
	
	/**	孫カテゴリ検索時のプルダウン表示用 **/
	@ModelAttribute(name = "grandChildSearchList")
	public Set<String> setGrandcategory(){
		return categoryService.getGrandChild();
	}
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private CategoryService categoryService;
	
	
	/** item一覧画面を表示
	 * @param pagination
	 * @param model
	 * @return itemリスト表示画面
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	@RequestMapping("/list")
	public String toShowItemList(ItemSearchForm itemSearchForm, Pagination pagination, Model model)  {
		ItemSearch itemSearch = new ItemSearch();
		BeanUtils.copyProperties(itemSearchForm, itemSearch);
		
		List<Item> itemList;
		try {
			itemList = itemService.search(itemSearch, pagination);
		} catch (Exception e) {
			model.addAttribute("falseSearch", "The search failed");
			return "list";
		} 
		pagination = itemService.paging(itemSearch, pagination);
		
		model.addAttribute("itemList", itemList);
		model.addAttribute("pagination", pagination);
		
		return "list";
	}
	
	
	/**
	 * item詳細画面を表示
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
	 * item新規追加画面を表示
	 * @return
	 */
	@RequestMapping("/add")
	public String toShowItemAddForm(Model model) {
		Set<String> parentCategoryList = categoryService.getParentCategory();
		model.addAttribute("parentCategoryList", parentCategoryList);
		return "add";
	}
	
	/**
	 * item新規追加を行い、item詳細画面を表示
	 * @return
	 */
	@RequestMapping("/add/comp")
	public String itemAdd(@Validated ItemAddForm itemAddForm, BindingResult result, RedirectAttributes redirectAttribute) {
		if(result.hasErrors()) {
			redirectAttribute.addFlashAttribute(itemAddForm);
			redirectAttribute.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + Conventions.getVariableName(itemAddForm), result);
			return "redirect:/item/add";
		}
		
		itemAddForm.setCategory();
		Item item = new Item();
		BeanUtils.copyProperties(itemAddForm, item);
		item.setPrice(Double.parseDouble(itemAddForm.getPrice()));
		
		itemService.register(item);
		
		return "redirect:/item/list";
	}

	/**
	 * item編集画面を表示
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
		Set<String> parentCategoryList = categoryService.getParentCategory();
		model.addAttribute("originalParentCategory", originalParentCategory);
		model.addAttribute("parentCategoryList", parentCategoryList);
		
		// childCategory
		String originalChildCategory = categoryArray[1];
		Set<String> childCategoryList = categoryService.getChildCategory(originalParentCategory);
		model.addAttribute("originalChildCategory", originalChildCategory);
		model.addAttribute("childCategoryList", childCategoryList);
		
		// grandCategory
		String originalGrandCategory = categoryArray[2];
		Set<String> grandCategoryList = categoryService.getGrandChild(originalChildCategory);
		model.addAttribute("originalGrandCategory", originalGrandCategory);
		model.addAttribute("grandCategoryList", grandCategoryList);
		
		return "edit";
	}
	
	/**
	 * itemを編集し、item詳細画面を表示
	 * @param itemEditForm
	 * @param result
	 * @param redirectAttribute
	 * @return
	 */
	@RequestMapping("/edit/comp")
	public String itemEdit(@Validated ItemEditForm itemEditForm, BindingResult result, RedirectAttributes redirectAttribute) {
		if(result.hasErrors()) {
			redirectAttribute.addFlashAttribute(itemEditForm);
			redirectAttribute.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + Conventions.getVariableName(itemEditForm), result);
			redirectAttribute.addAttribute("id", itemEditForm.getId());
			return "redirect:/item/edit";
		}
		
		itemEditForm.setCategory();
		
		Item item = new Item();
		BeanUtils.copyProperties(itemEditForm, item);
		item.setPrice(Double.parseDouble(itemEditForm.getPrice()));
		
		itemService.edit(item);
		
		redirectAttribute.addAttribute("id", itemEditForm.getId());
		return "redirect:/item/detail";
	}
	
	

}

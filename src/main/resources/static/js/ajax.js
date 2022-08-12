'use strict'

$(function(){
	$(document).on('change', '#parentCategory', function(){
		let url = 'http://localhost:8080/rest/item/getChildCategory';
		let selectedParentCategory = $('#parentCategory').val();
		let childCategoryOption = document.getElementById('childCategory');
		console.log(childCategoryOption);
		while(childCategoryOption.lastChild){
			childCategoryOption.removeChild(childCategoryOption.lastChild);
		}
		$.ajax({
			url: url,
			type:'post',
			dataType:'json',
			data:{
				parentCategory:selectedParentCategory
			},
			async:true,
		}).done(function(data){
			let childCategoryList = data;
			console.log(childCategoryList);
			
			let childCategory = document.getElementById("childCategory");
			for(let element of childCategoryList){
				let option = document.createElement('option');
				option.value = element;
				option.textContent = element;
				option.className = 'childCategoryOption';
				childCategory.appendChild(option);
			}
			
		}).fail(function(XMLHttpRequwst, textStatus, errorThrown){
			alert("エラー");
			console.log('XMLHttpRequwst:'+XMLHttpRequwst);
			console.log('textStatus:'+textStatus);
			console.log('errorThrown:'+errorThrown);
		});
	});
});

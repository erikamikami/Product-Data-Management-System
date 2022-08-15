'use strict'

/* parentCategory選択後、それに結びついたchildCategoryを表示させる */
$(function() {
	$(document).on('change', '#parentCategory', function() {
		let url = 'http://localhost:8080/rest/item/getChildCategory';
		let selectedParentCategory = $('#parentCategory').val();
		let childCategoryOption = document.getElementById('childCategory');
		while (childCategoryOption.lastChild) {
			childCategoryOption.removeChild(childCategoryOption.lastChild);
		}
		let grandCategory = document.getElementById("grandCategory");
		while(grandCategory.lastChild){
			grandCategory.removeChild(grandCategory.lastChild);
		};
		$.ajax({
			url: url,
			type: 'post',
			dataType: 'json',
			data: {
				parentCategory: selectedParentCategory
			},
			async: true,
		}).done(function(data) {
			let childCategoryList = data;
			console.log(childCategoryList);

			let childCategory = document.getElementById("childCategory");
			for (let element of childCategoryList) {
				let option = document.createElement('option');
				option.value = element;
				option.textContent = element;
				option.className = 'childCategoryOption';
				childCategory.appendChild(option);
			}

		}).fail(function(XMLHttpRequest, textStatus, errorThrown) {
			alert("エラー");
			console.log('XMLHttpRequwst:' + XMLHttpRequest);
			console.log('textStatus:' + textStatus);
			console.log('errorThrown:' + errorThrown);
		});
	});
});


/* childCategory選択後、それに結びついたgrandCategoryを表示させる */
$(function() {
	let childCategory = document.getElementById('childCategory');
	childCategory.addEventListener('change', function() {
		let grandCategory = document.getElementById("grandCategory");
		while(grandCategory.lastChild){
			grandCategory.removeChild(grandCategory.lastChild);
		};
		
		let url = "http://localhost:8080/rest/item/getGrandCategory";
		let selectedIndex = childCategory.selectedIndex;
		let selectedCildcategory = childCategory.options[selectedIndex].text;
		
		$.ajax({
			url:url,
			type:'post',
			dataType:'json',
			data:{
				childCategory:selectedCildcategory
			},
			async:true,
		}).done(function(data){
			let grandCategoryList = data;
			let grandCategory = document.getElementById('grandCategory');
			
			for(let element of grandCategoryList){
				let option = document.createElement('option');
				option.value = element;
				option.textContent = element;
				option.className = 'grandCategoryOption';
				grandCategory.append(option);
			}
		}).fail(function(XMLHttpRequest, textStatus, errorThrown){
			alert('エラー');
			console.log('XMLHttpRequest' + XMLHttpRequest);
			console.log('textStatus' + textStatus);
			console.log('errorThrown' + errorThrown);
		});
	});

});
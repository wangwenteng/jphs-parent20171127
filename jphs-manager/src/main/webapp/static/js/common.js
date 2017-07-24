window.onload = function() {
	$(".manager_header ul li").click(function() {
		$(".manager_header ul li").removeClass("active");
		$(this).addClass("active");
	})

	$(".common_left dt").click(function() {
		$(this).toggleClass("public_left_active");
		$(this).find("i").toggleClass("public-horn-45");
		$(this).find("span").toggleClass("common_icon_active");
		$(this).next(".public_left_list").slideToggle();

	})
	$(".public_left_list").find("dd").click(function() {
		$(".public_left_list").find("dd").removeClass("public_left_active");
		$(this).toggleClass("public_left_active");
	})
	/*左右互换*/

	var left = document.getElementById("left");
	var right = document.getElementById("right");
	left.onclick = function() {
		right.appendChild(left[left.selectedIndex])
	};

	right.onclick = function() {
		left.appendChild(right[right.selectedIndex])
	};
}
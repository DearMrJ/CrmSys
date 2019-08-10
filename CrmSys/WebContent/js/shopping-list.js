$(function(){
	//
	$('input[value="立即购买"]').click(function(){
		islogin();
	});
	$('input[value="加入购物车"]').click(function(){
		islogin();
	})
	function islogin(){
		if($('#username').text()=='登录'){
			alert("请先登录");
		}
		else{
			
		}
	}
	//数目的增加与减少
	$('img[src="img/plus01.gif"]').click(function(){
		var $number=parseInt($("#booke-number span").text());
		if ($number>=0) {
			$number++;
			$("#booke-number span").text($number);
		} else{
			return false;
		}
	})
	$('img[src="img/plus02.gif"]').click(function(){
		var $number=parseInt($("#booke-number span").text());
		if ($number>0) {
			$number--;
			$("#booke-number span").text($number);
		} else{
			return false;
		}
	})
	//收藏数量
	$('#shoucang-number').click(function(){
		var $num=parseInt($('#shoucang-number span').text());
		$('#shoucang-number span').text($num+1);
	})
	//请求服务器获取数据
	$.ajax({
			type: "GET",
			url: "js/pratice01.json",//请求的地址
			dataType: "json",
			timeout: 1000, 
			success: function(res) { 
				 $('#author span').append(res.author);
				 $('#book-name span').append(res.name);
				 $('#copyright span').append(res.copyright);
				 $('#book-price span').append(res.price)
			}
	});
})
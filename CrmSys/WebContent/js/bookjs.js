$(function () {
	$(window).scroll(function(){
		if($(this).scrollTop()>=50)
		{
			$(".right-nav li").each(function(index){
				$(this).animate({"right":"0px"},index*10);
			})
		}
		else
		{
		  $(".right-nav li").each(function(index){
				$(this).animate({"right":"-80px"},index*10);
			});
		}
	})
	/*这里是搜索栏获取焦点和失去焦点事件*/
	$(".txt").focus(function(){
		var $txt=$(".txt").val();
		if($(this).val()==$txt)
		{
			$(this).val("");
		}
	});
	$(".txt").blur(function(){
		if($(this).val()=="")
		{
			$(this).val("搜索一下");
		}
	});
	/*子菜单背景颜色随机*/
    /*function rc(){
      return parseInt(Math.random()*256);
     }
	$(".submenu li").hover(function(){
		$(this).css("background-color","rgb("+rc()+","+rc()+","+rc()+")");
		$(this).css("color","#FFFFFF");
	},
	function(){
	   $(this).css("background-color","#FFFFFF");
	   $(this).css("color","#000000");
	}
);*/
/*轮播图*/
  function animate(offset)
  {
  	var newLeft=parseInt($(".img-list").offset().left)+offset-$(".img-body").offset().left;
  	$(".img-list").css("left",newLeft+"px");
  	if(newLeft<-5510)
  	{
  			$(".img-list").css("left","-1102px");
  	}
  	if(newLeft>-1102)
  	{
  		$(".img-list").css("left","-5510px");
  	}
  }
  $("#next").click(function(){
  	animate(-1102);
  });
    $("#prev").click(function(){
  	animate(1102);
  });
  /*进行轮播*/
  var timer;
  function play()
  {
     timer=setInterval(function(){
     	animate(1102);
     },1500);
  }
  play();
  /*鼠标移动到图片上停止轮播*/
  var imgbody=document.getElementsByClassName("img-body");
  function stop()
  {
  	clearInterval(timer);
  }
  imgbody[0].onmouseover=stop;
  imgbody[0].onmouseout=play;
  /*Tab选项卡*/
  $('#b1').click(function(){
  	$('.Tab-cards1').css("left","0px")
  });
    $('#b2').click(function(){
  	$('.Tab-cards1').css("left","-1226px")
  })
       $('#b3').click(function(){
  	$('.Tab-cards1').css("left","-2452px")
  })
       $('#b4').click(function(){
  	$('.Tab-cards1').css("left","-3678px")
  })
      $('#b5').click(function(){
  	$('.Tab-cards1').css("left","-4904px")
  })
      
      $('#b6').click(function(){
  	$('.Tab-cards2').css("left","0px")
  });
    $('#b7').click(function(){
  	$('.Tab-cards2').css("left","-1226px");
  });
       $('#b8').click(function(){
  	$('.Tab-cards2').css("left","-2452px")
  });
       $('#b9').click(function(){
  	$('.Tab-cards2').css("left","-3678px")
  })
      $('#b10').click(function(){
  	$('.Tab-cards2').css("left","-4904px")
  })
  
});

/*显示时间*/
function time(){
    var vWeek,vWeek_s,vDay;
    vWeek = ["星期日","星期一","星期二","星期三","星期四","星期五","星期六"];
    var date =  new Date();
    year = date.getFullYear();
    month = date.getMonth() + 1;
    day = date.getDate();
    
    hours = date.getHours();
    hours = checkTime(hours);
    minutes = date.getMinutes();
    minutes = checkTime(minutes);
    seconds = date.getSeconds();
    seconds = checkTime(seconds);
    vWeek_s = date.getDay();
    var str=year+"-"+month+"-"+day+" "+vWeek[vWeek_s]+" "+hours+":"+minutes+":"+seconds;
    $(".time-content").text(str);//虚区
}
    setInterval("time()",1000);//调用自身，间隔一秒
       
       
/**格式化时钟**/
function checkTime(i){
	if(i<10&&i>=0){
		i="0"+i;
	}
	return i;
}
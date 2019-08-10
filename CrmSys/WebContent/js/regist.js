window.onload=function()
{
	var btn=document.getElementById("btn");//获取按钮
	btn.onclick=function()//添加按钮点击事件
	{	
		var Username = document.getElementById("username").value;//获取用户名
	    var Password = document.getElementById("password").value;//获取密码
	    var Email = document.getElementById("email").value;//获取密码
	    //判断输入是否正确在这个内容块写
	    /*****************************/
	   if(Username == "")
	   {
	   	alert("用户名不能为空");
	   	return false;
	   }
	   if(Password == "")
	   {
	   	alert("密码不能为空");
	   	return false;
	   }
	   if(Email == "")
	   {
		   alert("邮箱不能为空");
		   return false;
	   }else{
		   	var that = $('#password');
		   	console.log(Username);
		   	//获取公钥
			$.ajax({
				url:"user/getPublicKey",
				type:"post",
				data:{},
				success:function(result){//返回公钥
					var publicKey = result;
					//对密码进行加密
			        var encrypt = new JSEncrypt();
			        encrypt.setPublicKey(publicKey);
			        var password = that.val();
			        that.val(encrypt.encrypt(password));
			        $('#form').submit();
				}
			});
	   }
	}
}

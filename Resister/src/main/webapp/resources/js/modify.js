/**
 * 
 */
 $(function(){
	$("#changePwd").validate({
		rules:{
			password:{
				required:true,
				validPwd:true
			},
			new_password:{
				required:true,
				validPwd:true
			},
			confirm_password:{
				required:true,
				validPwd:true,
				equalTo:"#new_password"
			}
		},
		messages:{
			password:{
				required: "현재 비밀번호를 입력해주세요.",
			},
			new_password:{
				required: "새로운 비밀번호를 입력해주세요.",
			},			
			confirm_password:{
				required: "새로운 비밀번호를 입력해주세요.",
				equalTo:"이전 비밀번호와 다릅니다."
			}
		},
		errorPlacement:function(error,element){
			$(element).closest("form")
					  .find("small[id='"+element.attr('id')+"']")
			          .append(error);
		},
		success: function(label) {
			var name = label.attr('for');
			label.text(name+ ' is ok!');
		}
	});
})
$.validator.addMethod("validPwd",function(value){
	var regPwd = /^[A-za-z0-9]{5,15}/g;
	return regPwd.test(value);
},	"비밀번호를 확인해 주세요.");
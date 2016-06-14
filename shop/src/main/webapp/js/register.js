seajs.config({
	base:"mobile/js",
	alias: {
		'dialog': 'dialog.js',
		// sea-modules
		"zepto": "zepto.min.js"
	}
});
seajs.use(['zepto', 'dialog'], function($, dialog) {
	(function(window, $, undefined) {
		var identify_codeFlag = false,
			telCodeFlag = false,
			emailFlag = false,
			verValue = [],
			submitFlag = false;
		function showDialog(msg) {
			dialog({
				type: 'info',
				message: msg
			});
		}
		$(".code-img").click(function() {
			this.src = "validatecode?" + (new Date().getTime() + Math.random());
		});

		$(".lookPassword").click(function() {
			var input = $(this).parent().find("input");
			if (input.attr("type") == "password") {
				input.attr("type", "text");
			} else {
				input.attr("type", "password");
			}
		});
		//清空输入
		$('.btn-close').on('click', function(){
			$(this).parent().children('input[type=text],input[type=password],input[type=tel]').val('').focus();
			$(this).hide();
		});
		var handleCloseBtn = function(toShow){
			var $btn = $(this).parent().children('.btn-close');
			if(toShow === true){
				$btn.show();
				return
			}
			if(toShow === false){
				setTimeout(function(){
					$btn.hide();
				}, 0)
				return
			}
			if($.trim(this.value) === ''){
					$btn.hide();
			}else{
					$btn.show();
			}
		}
		$(".input-view .input-cell input").focus(function() {
			handleCloseBtn.call(this);
			errorMsg('');
		}).blur(function() {
			handleCloseBtn.call(this, false);
			checkData(this);
		}).on('input', function() {
			handleCloseBtn.call(this);
			if (this.name == "identify") {
				identify_codeFlag = false;
			} else if (this.name == "tel") {
				telCodeFlag = false;
			} else if (this.name == "email") {
				emailFlag = false;
			}
			checkData(this);
		});


		$(".tab-bar span").click(function() {
			if ($(this).hasClass("tab-active")) {
				return true;
			}
			$(this).addClass("tab-active");
			$(this).siblings("span").removeClass("tab-active");
			$(".input-view").removeClass("active");
			$("#" + $(this).attr("target-id")).addClass("active");
			$(".code-img").trigger("click");
		});
		function getValueAsName(ele) {
			if (!ele.length) {
				return false;
			}
			var returnData = {},
				flag = false;
			$("input", ele).each(function(i, item) {
				if (item.tagName == "INPUT") {
					flag = checkData(item);
					if (!flag) {
						return false;
					}
					returnData[item.name] = item.value;
				}
			});
			if (!flag) {
				return false;
			} else {
				return returnData;
			}
		}
		function errorMsg(text) {
			if (!text) {
				$("#registerError").html('').removeClass("pink");
			} else {
				$("#registerError").html(text).addClass("pink");
			}
		}

		function dialogMsg(msg) {
			dialog({
				type: 'info',
				message: msg
			});
		}

		function checkData(ele, blurCheck) {
				if (!ele) {
					return false;
				}
				var value = $.trim(ele.value);
				if (value && value == verValue[ele.name] && !submitFlag) {
					if ($("#registerError").html()) {
						return false;
					}
					return true;
				}
				verValue[ele.name] = value;
				switch (ele.name) {
					case "tel":
						if (!value || new RegExp("^((13[0-9])|(15[0-9])|(18[0-9])|14[0-9]|17[0-9])[0-9]{8,8}$").test(value) == false) {
							errorMsg('请输入正确的手机号');
							telCodeFlag = false;
							return false;
						} else if (!telCodeFlag) {
							var url = "checkPhone.do";
							$.ajax({
								url: url,
								data: {
									tel: value
								},
								type: 'post',
								success: function(json) {
									try {
										var json = eval("(" + json + ")");
									} catch (e) {
										errorMsg('请输入正确的手机号');
									}
									if(json.success) {
										telCodeFlag = true;
										errorMsg(json.errmsg);
									}else{
										errorMsg(json.errmsg);
										telCodeFlag = false;
									}
								}
							});
							return telCodeFlag;
						}
						return telCodeFlag;
						break;
					case "identify":
						if (!value || value.length < 4 || new RegExp("^([0-9a-zA-Z])+$").test(value) == false) {
							errorMsg('请输入正确的验证码');
							identify_codeFlag = false;
							return false;
						} else if (!identify_codeFlag && value.length == 4) {
							$.ajax({
								url: "checkCode.do?code=" + value,
								type: "GET",
								success: function(json) {
									try {
										var json = eval("(" + json + ")");
									} catch (e) {
										errorMsg('请输入正确的验证码');
									}
									if (json.success) {
										errorMsg('');
										identify_codeFlag = true;
									} else {
										identify_codeFlag = false;
										$(ele).focus();
										errorMsg('请输入正确的验证码');
									}
								}
							});
							return identify_codeFlag;
						}
						return identify_codeFlag;
						break;
					case "code":
						if (!value || value.length < 6 || new RegExp("^([0-9])+$").test(value) == false) {
							errorMsg('请输入正确的校验码');
							return false;
						} else if (value.length == 6 && !submitFlag) {
							errorMsg('');
							return true;
						}
						return true;
						break;
					case "password1":
					case "password2":
						if (!value || value.length < 6) {
							errorMsg('请输入正确的密码（密码长度最少6位以上）');
							return false;
						} else if (!submitFlag) {
							errorMsg('');
							return true;
						}
						return true;
						break;
					case "email":
						if (!value || value.length < 6 || /^([a-zA-Z0-9_\.-]+)@([a-zA-Z0-9\.-]+)\.([a-zA-Z\.]{2,6})$/.test(value) == false) {
							errorMsg('请输入正确的邮箱，例如：12345678@qq.com');
							emailFlag = false;
							return false;
						} else if (!emailFlag) {
							errorMsg('');
							var url = "/register/isEmailAvailable";
							$.ajax({
								url: url,
								data: {
									email: value
								},
								success: function(json) {
									try {
										var json = eval("(" + json + ")");
									} catch (e) {
										errorMsg('请输入正确的邮箱');
									}
									if (!json.success) {
										emailFlag = false;
										$(ele).focus();
										errorMsg(json.errmsg);
									} else {
										errorMsg('');
										emailFlag = true;
									}
								}
							});
							return emailFlag;
						}
						return emailFlag;
						break;
					default:
						return false;
						break;
				}
		}
		//手机发送动态码
		$(".get-code").bind("click", sendCode);
		$("#registerAndLogin").bind("click", function() {
			var data = {};
			submitFlag = true;
			
			if (!checkData(document.getElementById("tel")) || !checkData(document.getElementById("telIdentifyCode")) || !checkData(document.getElementById('telCode')) || !checkData(document.getElementById('telPwd'))) {
				submitFlag = false;
				return false;
			}
			data ={tel : document.getElementById("tel").value,
			telIdentifyCode : document.getElementById("telIdentifyCode").value,
			telCode : document.getElementById("telCode").value,
			telPwd : document.getElementById("telPwd").value
			}
			$.ajax({
				url: "regist.do",
				type: "post",
				data: data,
				success: function(json) {
					try {
						var json = eval("(" + json + ")");
					} catch (e) {
						errorMsg("请输入正确的信息&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
					}
					if (json.success) {
						$('#register').css("display","none");
						$('#show-success').css("display","block");
						var start = 5;
						var daojishi = setInterval(function(){
						    document.getElementById('daojishi-time').innerHTML=start;
						    start--;
						    if(start < 0){
						        clearInterval(daojishi);
						        window.location.href = "toIndex.do";
						        return;
						       }
						    },1000);
						} else {
						dialogMsg(json.errmsg);
					}
				}
			});

		});

		function sendCode() {
				submitFlag = true;
				var flag1 = checkData(document.getElementById("tel"));
				var flag2 = checkData(document.getElementById("telIdentifyCode"));
				submitFlag = false;
				if (flag1 && flag2) {
					$("#telCode").val('').attr("disabled");
					$(".get-code,.send-code-again").attr("disabled", true);
					$(".get-code,.send-code-again").unbind("click");
					var postData = {
						tel: $("#tel").val(),
						identify_code: $("#telIdentifyCode").val(),
						isReg:1
					}
					$.post('/ajax/sendMobileCode', postData, function(json) {
						try {
							var json = eval("(" + json + ")");
						} catch (e) {
							dialogMsg("动态口令发送失败，请稍候重试。");
							return;
						}
						if (json.success) {
							dialogMsg("动态口令已发送，15分钟内有效！");
							$("#telCode").removeAttr("disabled");
							$(".get-code,.send-code-again").hide();
							intervalTime(60);
						} else {
							$(".get-code,.send-code-again").removeAttr("disabled");
							$(".get-code,.send-code-again").bind("click", sendCode);
							switch (json.errno) {
								case -1:
									$("#tel").focus();
									errorMsg("请输入正确的手机号");
									return;
									break;
								case -2:
									$("#identify").focus();
									errorMsg("请输入正确的验证码&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
									return;
									break;
								case -3:
									dialogMsg("动态口令发送失败，请稍候重试。");
									return;
									break;
								default:
									dialogMsg("动态口令发送失败，请稍候重试。");
									break;
							}
						}
					});
				}
			}
			//end

		function intervalTime(num) {
			var num = parseInt(num);
			var i = 0;
			$(".send-code").css("display", "inline-block");
			$(".send-code span").text(num);
			var inter = setInterval(function() {
				if (i < num) {
					i++;
					$('.send-code span').text(num - i);
				} else {
					clearInterval(inter);
					$(".send-code-again").css("display", "inline-block").bind("click", sendCode);
					$(".send-code").hide();
					$(".send-code span").text(0);
				}
			}, 1000);
		}

	})(window, $);
});
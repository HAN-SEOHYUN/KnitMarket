/**
 * 
 */
 
 
window.Kakao.init('17794c6c3763c9ee2f66e9d03e0b9c5b'); // 인증요청

      function kakaoLogin() {
            window.Kakao.Auth.login({	// 토큰 요청
                scope: 'profile_nickname, account_email', //동의항목 페이지에 있는 개인정보 보호 테이블의 활성화된 ID값을 넣습니다.
                success: function(response) {
                    console.log(response) // 로그인 성공하면 받아오는 데이터
                    window.Kakao.API.request({ // 사용자 정보 가져오기 
                        url: '/v2/user/me',
                        success: (res) => {
                            const kakao_account = res.kakao_account;
                            console.log(kakao_account);
                            alert(kakao_account);
                            
                            var email = kakao_account.email;
                            var name = res.properties.nickname;
                            
                            $('#frmLogin input#member_id').val(email);
                            //alert("이메일폼에담기"+ email);
                            $('#frmLogin input[name=name]').val(name);
                            //alert("닉네임폼에담기");
                            //alert("폼전송");
                            $('form[name=frm]').submit();
                        }
                    });
                    //window.location.href='/herb/kakao' //리다이렉트 되는 코드
                },
                fail: function(error) {
                    console.log(error);
                }
            });
        }
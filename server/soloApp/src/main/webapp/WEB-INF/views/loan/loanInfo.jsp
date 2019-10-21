<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>boardList</title>
</head>
<body>
<div class="page-wrapper">
    <div class="container-fluid">
        <div class="col-lg-8"><!--게시판 넓이 -->
            <div class="col-lg-12">
                <h1 class="page-header">대출 정보 관리</h1>
            </div>
            <div class="row">
                  <div class="col-lg-12">
                     
                  </div>
              </div>
            <div class="panel panel-default">
                <div class="panel-heading">대출 정보 </div>
                <div class="panel-body">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>이름</th>
                                <th>주민등록번호</th>
                                <th>대출 한도</th>
                                <th>대출 금액</th>
                                <th>승인 여부</th>
                                <th>승인 버튼 </th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>강성지</td>
                                <td>900805-1234567</td>
                                <td>36000000</td>
                                <td>5000000</td>
                                <td>승인완료</td>
                                <td>
                                	<form method="post" action="">
    								<input type="hidden" name="name" value="your value"/>
    								<button type="submit">승인 하기</button>
									</form>
								</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

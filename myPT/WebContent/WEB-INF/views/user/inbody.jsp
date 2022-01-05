<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"/>

    <title>인바디</title>
    <link href="/myPT/css/styles.css" rel="stylesheet" />
    <link href="/myPT/css/index.css" rel="stylesheet"/>
    <link href="/myPT/css/widget.css" rel="stylesheet"/>
    <link href="/myPT/css/inbody.css" rel="stylesheet"/>

    <script
      src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"
    ></script>

  </head>
  <body class="sb-nav-fixed">
    <!-- nav -->
    <jsp:include page="/includeFiles/nav.jsp"></jsp:include>
    <!-- nav -->

    <!-- QR 모달 -->
    <jsp:include page="/includeFiles/modalQR.jsp"></jsp:include>
    <!--//QR 모달-->

    <div id="layoutSidenav">
      <!-- sideNav -->
      <jsp:include page="/includeFiles/userSideNav.jsp"></jsp:include>
      <!-- /sideNav -->
      
      
       <div id="layoutSidenav_content">
         
         <main>
       		 <div class="container-fluid text-center">
	              <div class="row border border-dark rounded-top my-3 mx-auto p-1" id="profile">
	                  <div class="col border-right border-dark" id="userName">
	                  </div>
	                  <div class="col border-right border-dark" id="age">
	                  </div>
	                  <div class="col border-right border-dark" id="gender">
	                  </div>
	                  <div class="col" id="height">
	                  </div>
	              </div>
	                  
	               
	                
              <div class="card mb-4">
              	   <div class="card-footer bg-dark text-white">
                 	측정일: <select id="measureDayForInbodyChart" class="col-sm-12 col-md-5" onchange="measureDayForInbodyIsChanged()"> 
                    	</select> 
                  </div>
                  <div class="card-body p-1" id="inbodyChartWrapper" style="width:100%; height:250px;">
                      <canvas id="inbodyChart"></canvas>
                  </div>
<!-- 	                  <div class="card-footer"> -->
<!-- 	                 측정일: <select id="measureDayForInbodyChart" class="col-sm-12 col-md-5" onchange="measureDayForInbodyIsChanged()">  -->
<!-- 	                    </select>  -->
<!-- 	                  </div> -->
              </div>
	             
	
		         <div id="datasWithCharts">    
		          <div class="card-deck mb-4">
	                  <div class="card pt-3">
	                      <h6 class="card-title w-100">체중(kg)</h6>
	                  			<canvas id="weightChart"></canvas>  
	                  </div>
	
	                  <div class="card pt-3">
	                      <h6 class="card-title w-100">골격근량(kg)</h6>
	                  <canvas id="muscleChart"></canvas>  
	                  </div>
	
	                  <div class="card pt-3">
	                      <h6 class="card-title w-100">체지방량(kg)</h6>
	                      <canvas id="fatChart"></canvas>                            
	                  </div>
             	 </div>
             	 

                  <div class="card border-secondary mb-4 col-12 p-0" id="comparison">
                      <div class="card-header bg-dark text-white">

                          <div class="row">
                             	  <div class="col"></div>

	                              <div class="col">
                              		  <div id="showBeforeDay"></div>
                                      <select id="beforeMeasureDay" class="col-sm-12 col-md-10" onchange="beforeDayIschanged()"> 
                                      <option value="default" disabled selected>비교 측정일1</option>   
                                  	  </select>   	  
	                              </div>
	                              
	                              <div class="col">
                              		  <div id="showAfterDay"></div>
                              		  <select id="afterMeasureDay" class="col-sm-12 col-md-10" onchange="afterDayIschanged()"> 
                                      <option value="default" disabled selected>비교 측정일2</option>   
                                  	  </select>	                               	  
	                              </div>
	                              <div class="col">차이</div>
	                          </div>
                      </div>

                      <div class="card-body p-sm-1 p-md-2 d-flex align-items-center">
                          <div class="w-100">
                              <div class="row">
                                  <div class="col">체중</div>
                                  <div class="col"><span class="before weight" id="beforeWeight"></span>kg</div>
                                  <div class="col"><span class="after" id="afterWeight"></span>kg</div>
                                  <div class="col"><span class="result"></span>kg</div>
                              </div>
                              <div class="row">
                                  <div class="col">골격근량</div>
                                  <div class="col"><span class="before muscle" id="beforeMuscle"></span>kg</div>
                                  <div class="col"><span class="after" id="afterMuscle"></span>kg</div>
                                  <div class="col"><span class="result"></span>kg</div>
                              </div>
                              <div class="row">
                                  <div class="col">체지방량</div>
                                  <div class="col"><span class="before fat" id="beforeFat"></span>kg</div>
                                  <div class="col"><span class="after" id="afterFat"></span>kg</div>
                                  <div class="col"><span class="result"></span>kg</div>
                              </div>    
                          </div>
                      </div>
                    </div>
                 </div> 
           </div>          
        </main>       
     
     </div>    
  </div>


    <script
      src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script
      src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js">
    </script>
    <script src="/myPT/js/scripts.js"></script>
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.js"></script>
   
    <script src="/myPT/js/chartjs-plugin-datalabels.js"></script>
    <script src="/myPT/js/inbody.js"></script>
    
    
  </body>
</html>

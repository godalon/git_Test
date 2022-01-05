function deleteUser(id){
	if(confirm("정말 삭제하시겠습니까?")==true){
		location.href="adminUserDelete.do?id="+id;
	}else{
		return false;
	}
}

function deleteTrainer(t_id){
	if(confirm("정말 삭제하시겠습니까?")==true){
		location.href="adminTrainerDelete.do?t_id="+t_id;
	}else{
		return false;
	}
}

function deleteHistory(hid){
	if(confirm("정말 삭제하시겠습니까?")==true){
		location.href="adminHistoryDelete.do?hid="+hid;
	}else{
		return false;
	}
}

function deleteSchedule(s_id,s_date){
	if(confirm("정말 삭제하시겠습니까?")==true){
		location.href="adminScheduleDelete.do?s_id="+s_id+"&s_date="+s_date;
	}else{
		return false;
	}
}
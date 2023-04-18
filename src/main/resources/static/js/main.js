function applicationRowFocusIn(focusedRow) {
	var activeStage = parseInt(focusedRow.cells[4].textContent);
	for(let i = 1; i <= activeStage; i++) {
		if(i < activeStage) {
			document.getElementById('stepper-stage-' + i).classList.add("completed");
		} else {
			document.getElementById('stepper-stage-' + i).classList.add("active");
		}
	}
	focusedRow.classList.add("selected");
	document.getElementById("empty-stage-status").style.display = "none";
	document.getElementById("show-stage-status").style.display = "flex";
}

function applicationRowFocusOut(focusOutRow) {
	var activeStage = parseInt(focusOutRow.cells[4].textContent);
	for(let i = 1; i <= activeStage; i++) {
		if(i < activeStage) {
			document.getElementById('stepper-stage-' + i).classList.remove("completed");
		} else {
			document.getElementById('stepper-stage-' + i).classList.remove("active");
		}
	}
	focusOutRow.classList.remove("selected");
	document.getElementById("empty-stage-status").style.display = "flex";
	document.getElementById("show-stage-status").style.display = "none";
}

function generateDepartmentFields() {
  var noOfDepartment = document.getElementById('noOfDepartment').value;
  if(isNaN(noOfDepartment)) {
	  alert('Value in NumberOfDepartment is not a valid number !!!')
	  return;
  }
  var container = document.getElementById('container');
  while (container.hasChildNodes()) {
    container.removeChild(container.lastChild);
  }
  for (i=0;i<parseInt(noOfDepartment);i++){
    for(j=0;j<3;j++){
      var input = document.createElement('input');
      input.setAttribute('type', 'text');
      input.setAttribute('class', 'form-control input-lg');
      if(j == 0) {
        input.setAttribute('placeholder', 'Department Code ' + (i + 1));
      } else if(j == 1 || j == 2) {
       	input.setAttribute('disabled', true);
      }
      container.appendChild(input);
    }
    container.appendChild(document.createElement("br"));
  }
}

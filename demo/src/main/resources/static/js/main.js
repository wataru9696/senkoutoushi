function valueChange(event) {
    const minNumId = 'p' + this.value + '-min-num';
    const targetMinNum = document.getElementById(minNumId);
    const maxNumId = 'p' + this.value + '-max-num';
    const targetMaxNum = document.getElementById(maxNumId);
    if (this.checked) {
        targetMinNum.value = '';
        targetMaxNum.value = '0';
    } else {
        targetMaxNum.value = '';
    }
}

let checkboxes = document.getElementsByClassName("check");
let checkboxArray = Array.from(checkboxes);
checkboxArray.forEach(checkbox => {
    checkbox.addEventListener('change', valueChange);
});

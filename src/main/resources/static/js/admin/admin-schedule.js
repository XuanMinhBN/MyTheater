// Get today's date in the format YYYY-MM-DD
const today = new Date().toISOString().split('T')[0];
// Set the value of the date input to today's date
document.getElementById('inputStartDate').value = today;
document.getElementById('inputEndDate').value = today;
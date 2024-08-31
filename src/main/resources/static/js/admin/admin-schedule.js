// Get today's date in the format YYYY-MM-DD
const today = new Date();
const offset = new Date(today.getTime() - today.getTimezoneOffset() * 60000).toISOString().split('T')[0];
// Set the value of the date input to today's date
document.getElementById('inputStartDate').value = today;
document.getElementById('inputEndDate').value = today;
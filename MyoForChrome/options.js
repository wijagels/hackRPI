// Saves options to chrome.storage
function save_options() {
  var ac1 = document.getElementById('ac1').value;
  chrome.storage.sync.set({
    ac1: ac1
  }, function() {
    // Update status to let user know options were saved.
    var status = document.getElementById('status');
    status.textContent = 'Options saved.';
    setTimeout(function() {
      status.textContent = '';
    }, 750);
  });
}

// Restores select box and checkbox state using the preferences
// stored in chrome.storage.
function restore_options() {
  // Use default value color = 'red' and likesColor = true.
  chrome.storage.sync.get({
    ac1: 'scrollup',
  }, function(items) {
    document.getElementById('ac1').value = items.ac1;
  });
}
document.addEventListener('DOMContentLoaded', restore_options);
document.getElementById('save').addEventListener('click',
    save_options);
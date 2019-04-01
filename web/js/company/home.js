/**
 * Created by Administrator on 2017-05-15.
 */
$(document).ready(function () {

    $("#checkinTable").footable();
    $("#appTable").footable();
    $("#remindTable").footable();
    $("#complaintTable").footable();
    $("#companyTable").footable();
    $("#intentionTable").footable();
});

function searchApp() {
    parent.showAppointmentPage();
}
function searchCheckin() {
    parent.showCheckinPage();
}
function searchRemind() {
    parent.showRemindPage();
}
function searchComplaint() {
    parent.showComplaintPage();
}
function searchCompany() {
    parent.showCompanyPage();
}
function searchIntention() {
    parent.showIntentionPage();
}

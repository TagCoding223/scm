function exportData(){
    console.log("export data script")

    TableToExcel.convert(document.getElementById("contact-table"),{
        name: "contact.xlsx",
        sheet: {
            name: "Sheet 1",
        },
    });


}
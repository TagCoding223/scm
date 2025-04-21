console.log("contact.js hello")
const baseURL = "http://localhost:8080";
const viewContactModal = document.getElementById('view_contact_modal');


// options with default value
const options = {
    placement: 'bottom-right',
    backdrop: 'dynamic',
    backdropClasses: 'bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40',
    closable: true,
    onHide: ()=> {
        console.log('modal is hidden');
    },
    onShow: () => {
        console.log('modal is shown');
    },
    onToggle: () => {
        console.log('modal is toggled');
    },

};

// instance options object
const instanceOptions = {
    id: 'view_contact_modal',
    override: true
};

const contactModal = new Modal(viewContactModal, options, instanceOptions);

function openContactModal(){   
    contactModal.show();
}

function closeContactModal(){
    contactModal.hide();
}

async function loadContactData(id){
    console.log("Contact id: "+id);

    // fetch(`http://localhost:8080/api/contacts/${id}`).then( async (response) =>{
    //     const data = await response.json();
    //     // console.log(data);
    //     return data;
    // }).catch((error)=>{
    //     console.log(error);
    // })

    try {
        const data = await( await fetch(`${baseURL}/api/contacts/${id}`)).json();
        console.log(data);
        console.log(data.websiteLink.startsWith('https://'));

        document.getElementById('contact_name').innerHTML = data.name;
        document.getElementById('contact_email').innerHTML = data.email;
        document.getElementById('contact_pic').src = data.picture!=null ? data.picture : '/images/default_profile_pic.svg';
        document.getElementById('contact_phoneNumber').innerHTML = data.phoneNumber;
        document.getElementById('contact_desc').innerHTML = data.description;
        document.getElementById('contact_address').innerHTML = data.address;
        document.getElementById('contact_fav').innerHTML = data.favorite == false ? '<i class="fa-regular fa-star"></i>' : '<i class="fa-solid fa-star text-yellow-400"></i>';
        
        document.getElementById('contact_web_link').innerHTML = data.websiteLink == null ? '' : data.websiteLink;
        document.getElementById('contact_web_link').href = data.websiteLink == null ? '' : (data.websiteLink.startsWith('https://') ? data.websiteLink : 'https://'+data.websiteLink);

        document.getElementById('contact_linkedin').innerHTML = data.linkedinLink == null ? '' : data.linkedinLink;
        document.getElementById('contact_linkedin').href = data.linkedinLink == null ? '' : (data.linkedinLink.startsWith('https://') ? data.linkedinLink : 'https://'+data.linkedinLink);

        
        openContactModal();
    } catch (error) {
        console.log(error);
    }
}

// delete contact alert
function deleteContact(id){
    Swal.fire({
        title: "Do you want to delete the contact?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonText: "Delete",
      }).then((result) => {
        /* Read more about isConfirmed, isDenied below */
        if (result.isConfirmed) {
            const url = `${baseURL}/user/contacts/delete/${id}`;
            window.location.replace(url);
        //   Swal.fire("Saved!", "", "success");
        }
      });
}
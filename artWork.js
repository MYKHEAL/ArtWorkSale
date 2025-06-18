window.addEventListener("DOMContentLoaded", () => {
  const loggedIn = localStorage.getItem("isLoggedIn");
  if (loggedIn === "true") {
    showHomePage();
  }
});


let currentForm = "register";

function toggleFormToggle() {
  if (currentForm === "register") {
    document.getElementById("register-form").style.display = "none";
    document.getElementById("login-form").style.display = "block";
    document.querySelector(".toggle").innerText = "Switch to Register";
    document.getElementById("form-title").innerText = "Login";
    currentForm = "login";
  } else {
    document.getElementById("login-form").style.display = "none";
    document.getElementById("register-form").style.display = "block";
    document.querySelector(".toggle").innerText = "Switch to Login";
    document.getElementById("form-title").innerText = "Register";
    currentForm = "register";
  }
}




function register() {
  const name = document.getElementById("reg-name").value;
  const email = document.getElementById("reg-email").value;
  const phoneNumber = document.getElementById("reg-phoneNumber").value;
  const password = document.getElementById("reg-password").value;

  fetch("http://localhost:8080/api/users/register", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({ name, email, phoneNumber, password })
  })
    .then(response => {
      if (!response.ok) {
        return response.text().then(error => {
          throw new Error("Server error: " + error);
        });
      }
      return response.json();
    })
    .then(data => {
      alert(data.message || "Registered successfully!");
      showHomePage();
    })
    .catch(error => {
      console.error("Error:", error);
      alert("An error occurred: " + error.message);
    });
}








function login() {
  const email = document.getElementById("login-email").value;
  const password = document.getElementById("login-password").value;

  if (!email || !password) {
    alert("Please enter email and password.");
    return;
  }

  fetch("http://localhost:8080/api/users/login", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({ email, password })
  })
    .then(response => {
      if (!response.ok) {
        return response.text().then(error => {
          throw new Error("Login error: " + error);
        });
      }
      return response.json();
    })
    .then(data => {
  localStorage.setItem("isLoggedIn", "true");
  localStorage.setItem("userEmail", email); // optional

  alert(data.message || "Login successful!");
  showHomePage();
})
    .catch(error => {
      console.error("Login error:", error);
      alert("An error occurred: " + error.message);
    });
  }


 function showHomePage() {
  document.querySelector(".access-container").style.display = "none";
  document.getElementById("home-page").style.display = "block";
   fetchAndDisplayArtworks();
}


function logout() {
   localStorage.removeItem("isLoggedIn");
  localStorage.removeItem("userEmail");
  document.getElementById("home-page").style.display = "none";
  document.querySelector(".access-container").style.display = "flex";
  toggleFormToggle(); // Optional: go back to login view
}



function searchArtworks() {
  const keyword = document.getElementById("search-input").value.trim();

  if (!keyword) {
    alert("Please enter a search keyword");
    return;
  }

  fetch(`http://localhost:8080/api/artworks/search?keyword=${encodeURIComponent(keyword)}`)
    .then(response => {
      if (!response.ok) throw new Error("Search failed");
      return response.json();
    })
    .then(data => {
      displayArtworks(data); 
    })
    .catch(error => {
      alert("Error during search: " + error.message);
    });
}





function createArtwork(event) {
  event.preventDefault();

  const title = document.getElementById("art-title").value;
  const description = document.getElementById("art-description").value;
  const imageUrl = document.getElementById("art-image-url").value;
  const category = document.getElementById("art-category").value;
  const price = parseFloat(document.getElementById("art-price").value);
  const artistName = document.getElementById("art-artistName").value;
  const isAvailable = document.getElementById("art-isAvailable").checked;

  const artworkData = {
    title,
    description,
    imageUrl,
    category,
    price,
    artistName,
    available: isAvailable
  };

  fetch("http://localhost:8080/api/artworks", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(artworkData)
  })
    .then(response => {
      if (!response.ok) {
        return response.text().then(error => { throw new Error(error); });
      }
      return response.json();
    })
    .then(data => {
      alert("Artwork created successfully!");
      fetchAndDisplayArtworks(); 
    })
    .catch(error => {
      alert("Error creating artwork: " + error.message);
      console.error("Error:", error);
    });
}









function fetchAndDisplayArtworks() {
  fetch("http://localhost:8080/api/artworks")
    .then(response => {
      if (!response.ok) throw new Error("Failed to fetch artworks");
      return response.json();
    })
    .then(displayArtworks)
    .catch(error => {
      console.error("Error loading artworks:", error);
      document.getElementById("art-list").innerHTML = "<p>Unable to load artworks.</p>";
    });


}





function showSection(section) {
  document.getElementById("home-section").style.display = "none";
  document.getElementById("create-section").style.display = "none";
  document.getElementById("details-section").style.display = "none";

  if (section === "home") {
    document.getElementById("home-section").style.display = "block";
    fetchAndDisplayArtworks();


  } else if (section === "create") {
    document.getElementById("create-section").style.display = "block";



  }else if (section === "details") {
    document.getElementById("details-section").style.display = "block";
  }


}







function displayArtworks(artworks) {
  const artList = document.getElementById("art-list");
  artList.innerHTML = "";

  if (!artworks || artworks.length === 0) {
    artList.innerHTML = "<p>No artworks found.</p>";
    return;
  }

  artworks.forEach(art => {
    const card = document.createElement("div");
    card.className = "art-card";
    card.innerHTML = `
      <img src="${art.imageUrl}" alt="${art.title}" />
      <div class="info">
        <h3>${art.title}</h3>
        <p>${art.description}</p>
        <p><strong>₦${art.price}</strong></p>
        <p>By: ${art.artistName}</p>
        <button onclick="viewDetails('${art.id}')">View Details</button>

      </div>
    `;
    artList.appendChild(card);
    console.log("art.id =", art.id);

  });
}





function viewDetails(artworkId) {
    console.log("Trying to fetch details for ID:", artworkId);

  fetch(`http://localhost:8080/api/artworks/${artworkId}`)
    .then(response => {

      if (!response.ok) throw new Error("Artwork not found");
      return response.json();
    })
    .then(art => {
              console.log("Loaded artwork:", art);  // Add this to debug


      const detailsDiv = document.getElementById("artwork-details");
      detailsDiv.innerHTML = `
        <h3>${art.title}</h3>
        <img src="${art.imageUrl}" alt="${art.title}" />
        <p><strong>Description:</strong> ${art.description}</p>
        <p><strong>Category:</strong> ${art.category}</p>
        <p><strong>Price:</strong> ₦${art.price}</p>
        <p><strong>Artist:</strong> ${art.artistName}</p>
        <p><strong>Available:</strong> ${art.available ? "Yes" : "No"}</p>
        <button onclick="showOrderForm(${art.id})">Order This Artwork</button>
      `;
      showSection("details");
    })
    .catch(error => {
      alert("Error loading artwork details: " + error.message);
    });
}



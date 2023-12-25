<template>
    <div class="wrapper">
      <input type="checkbox" id="btn" hidden />
      <label for="btn" class="menu-btn">
        <i class="fa-solid fa-bars"></i>
      </label>
      <nav id="sidebar">
        <div class="title">Menu</div>
        <ul class="list-items">
          <router-link to=""></router-link>
          <li><router-link to="/Home">Inbox</router-link></li>
        <li><router-link to="/Contacts">Contacts</router-link></li>
        <li><router-link to="/Folder">Folders</router-link></li>
        <li><router-link to="/Draft">Draft</router-link></li>
        <li><router-link to="/Sent">Sent</router-link></li>
        <li><router-link to="/Trash">Trash</router-link></li>
          <div class="icons" style="diplay: flex; flex-direction: column">
            <h4 style="color: aliceblue">{{ user_name }}</h4>
            <h6 style="color: aliceblue">{{ email }}</h6>
          </div>
        </ul>
      </nav>
    </div>
    <div class="content">
      <div class="header">
        <h1 style="color: aliceblue; padding-top: 10px">O3M-Mail</h1>
        <button style="width:auto; font-size:large;  background-color:darkgrey;  height:40px;margin:auto; margin-left:30px ;" @click="reload()">reload</button>
        <button style="width:auto; background-color:darkgrey; border-radius: 10px; font-size:large; height:50px;margin:auto;margin-left:800px ;" @click="setting()">Setting <i  style="font-size:40px" class="fa-solid fa-gear"></i></button>
      </div>
      <div class="b2">
        <div class="container">
          <div class="operations">
            <div class="filter">
             
              <div class="search">
                <select v-model="selector"  id="searchtypeid" name="searchtype">
                  <option v-for="folder in folders_name" :key="folder.name" :option= folder_name >{{ folder.name }}</option>
                </select>
                <button @click="select()" >select</button>
              </div>
            </div>
           

          </div>
          <v-dialog v-model="dialog" width="800" heigth="850"  dark hide-overlay persistent>
            <v-card>
              <v-card-title style="color:white; background-color:#3498db; padding:auto; text-align:center; font-size:35px">new massage</v-card-title>
              <v-card-text style="background-color:black">
                <v-form>
                  <label style="font-size:20px; font:bold; color:#3498db;background-color:black">To:</label>
                  <br>
                  <input v-model="massage.receiver" style="width:600px" type="text" placeholder=" user@example.com" :value="this.itemmail"  id="toid" disabled>
                  <br>
                  <label style="font-size:20px; font:bold; color:#3498db;background-color:black">From:</label>
                  <br>
                  <input v-model="massage.sender" style="width:600px" type="text" placeholder=" useremail" :value="email"  disabled>
                  <br>
                  <label style="font-size:20px; font:bold; color:#3498db;background-color:black">Subject:</label>
                  <br>
                  <input v-model="massage.subject" style="width:600px" type="text" placeholder=" (0-30)characters">
                  <br>
                  <label style="font-size:20px; font:bold; color:#3498db;background-color:black ">contentent</label>
                  <br>
                  <textarea v-model="massage.content" id="userInput" name="userInput" class="left-up-align" rows="2" style="width:600px;border-raduis:10px;"></textarea>
                </v-form>
                <label style="font-size:20px; font:bold; color:#3498db;background-color:black" for="#" >attachments:</label>
              </v-card-text>
              <input style="padding-left:30px"  type="file"  multiple @change="handleFileChange">
              <v-card-actions style="background-color:black">
                
                <label style="font-size:20px; font:bold; color:#3498db;background-color:black" for="menu">priority:</label>
                <select id="menu" name="menu" v-model="massage.priority">
                  <option value="1">1 (High)</option>
                  <option value="2">2</option>
                  <option value="3">3</option>
                  <option value="4">4</option>
                  <option value="5">5 (Low)</option>
                </select>
                <v-btn style="width:auto; margin-left:20px" @click="draft()">Save as a draft</v-btn>
                <v-btn style="width:auto; margin-left:200px" @click="send()">Send</v-btn>
                <v-btn style="width:auto; margin-left:60px" @click="dia()">close</v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
          <!-- ///////////////////////////////////////////////////////////// -->
          <v-dialog v-model="edit" width="600" heigth="600"  dark hide-overlay persistent>
            <v-card>
              <v-card-title style="color:white; background-color:#3498db; padding:auto; text-align:center; font-size:35px">edit<i class="fa-solid fa-pen-to-square"></i></v-card-title>
              <v-card-text style="background-color:black">
                <v-form>
                    <label style="font-size:20px; font:bold; color:#3498db;background-color:black">new name:</label>
                    <br>
                    <input v-model="this.newname" style="width:600px" type="text" placeholder=" new name"   id="toid" >
                    <br>
                    <label style="font-size:20px; font:bold; color:#3498db;background-color:black">email:</label>
                    <br>
                    <input style="width:600px" type="text" :value="this.itemmail"  disabled>
                   
                  <v-btn style="width:auto;margin-top:15px; margin-left:10px" @click="save(this.itemmail)">Save</v-btn>
                  </v-form>
              </v-card-text>
  
              <v-card-actions style="background-color:black">
               
                <v-btn style="width:auto; margin-left:500px" @click="close_edit()">close</v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
           <!-- // ///////////////////////////////////////////////////////-->
          <v-dialog v-model="sittingdialog" width="600" heigth="600"  dark hide-overlay persistent>
            <v-card>
              <v-card-title style="color:white; background-color:#3498db; padding:auto; text-align:center; font-size:35px">Setting  </v-card-title>
              <v-card-text style="background-color:black">
                <v-form>
                  <label style="font-size:20px; font:bold; color:#3498db;background-color:black">status</label>
                  <select id="menu" name="menu" v-model="status">
                    <option value="online">online</option>
                    <option value="in calling">in calling</option>
                    <option value="busy">busy</option>
                  </select>
                  <br>
                  <v-btn style="width:auto;margin-top:15px; margin-left:10px" @click="logout()">LOgout<i style="font-size:25px"  class="fa-solid fa-right-from-bracket"></i></v-btn>
                  </v-form>
              </v-card-text>
              <v-card-actions style="background-color: black; display: flex; justify-content:right;">
                <v-btn style="width: auto;" @click="applay()">Apply</v-btn>
                <v-btn style="width: auto;" @click="closesitting()">Close</v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
          <div class="info">
            <div style=" text-shadow:0 0 0 15px; #08daff; text-align: center; width:100%"><h1 class="grad">{{ selector }}</h1></div>
               
            <table border="2px">
                 <tr>
                  <td>priority</td>
                  <td>Sender</td>
                  <td>Date</td>
                  <td> Subject</td>
                  <td>Content</td>
                  <td>Attachments</td>
                  <td>Move to trash <i class="fa-solid fa-trash" style="font-size:15px; color:red"></i></td>
                 
                </tr>
                <tr v-for="item in emails" :key="item.email">
                  <td>{{item.priority}}</td>
                  <td>{{item.sender}}</td>
                  <td>{{item.date}}</td>
                  <td> {{item.subject}}</td>
                  <td>{{item.Content}}</td>
                  <td><a :href="item.attachments">{{ item.attachments }}</a></td>
                  <td><i @click="trash(item.index)" class="fa-solid fa-trash" style="font-size:25px; color:red;"></i></td>
                </tr>
               </table>
          </div>
          <div class="usersinformation">
            <div class="box" v-for="user in users" :key="user.id">
              <h1>{{ user }}</h1>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script >

//   import "@fortawesome/fontawesome-free/css/all.css"; // Import the styles
  export default {
    name: "FolDers",
    data() {
      return {
        user_name: "mohamed hassan",
        email: "mohamed@gmail.com",
        dialog:false,
        selector:'',
        newname:'',        
        sittingdialog:false,
        massage:{
        sender:'',
        receiver:'',
        subject:'',
        content:'',
        priority:'',
        attachments:[],
      },
        folders_name:[
            {
                name:'work',
            },
            {
                name:'social'
            },
            {
                name:'family'
            },
            {
                name:'frinds'
            },
            ],
        emails:[],
      };
    },
    // mounted(){
    //   if(JSON.parse(localStorage.getItem("person-inf")).userName==null)
    //     this.$router.push('/');
    // },
    methods: {
      dia(itemmail){
        this.dialog=!this.dialog
        this.itemmail=itemmail
      },
      setting(){
        this.sittingdialog=true
      },
      closesitting(){
        this.sittingdialog=false
      },
      applay(){
        JSON.stringify(localStorage.getItem("user-info")).status=this.status 
        this.sittingdialog=false     
      },
      logout(){
        localStorage.clear();
        this.$router.push('/')
      },
      reload(){
        location.reload();
      },
     async send(){
        localStorage.setItem("massage",JSON.stringify(this.massage))
        this.dialog=false
        await fetch("http://localhost:8080/send",{
          method:"POST",
          body:JSON.stringify(this.massage)
        })
  
      },
     async select(){
     await fetch(`http://localhost:8080/    ?file_selected=${this.selector}`,{
          method:"GET"
     }).then(res=>res.json())
     .then(data=>this.emails=data);
      
      },
      //remove this contact 
     async trash(ind){
        console.warn(JSON.stringify("delete "+ind));
        fetch("http://localhost:8080/ ",{
          method:"POST",
          body:JSON.stringify(ind)
        });
         await fetch(`http://localhost:8080/  ?index=${ind}`,{
          method:"DELETE"
       }).then(res=>res.json())
     .then(data=>this.emails=data);
     },
    
     }
  };
  </script>
  
  <style scoped>
  @import url("https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700&display=swap");
  input[type="file"] {
    color: #3498db; 
    background-color: black;
    padding: 10px; 
    
  }
  .new {
    width:auto;
    height: 25px;
    margin-top: 5px;
    margin-left: 15px;
    background-color:#3498db; 
    color: white; 
    text-align: center; 
    font-size: 15px; 
    cursor: pointer; 
    transition: transform 0.3s ease-in-out; 
  }
  
  .new:hover {
    transform: scale(1.1); /* Scale the button on hover */
  }
  table {
    border-collapse: collapse;
    width: 100%;
    box-shadow: 0 0 10px #3498db;
   
  }
  
  table, th, td {
    border: 2px solid rgb(19, 231, 223);
    
  }
  
  th, td {
    padding: 10px;
    text-align: left;
  }
  
  tr:hover {
    background-color: #f5f5f5;
  }
  
  td {
    transition: background-color 0.3s ease;
    background-color: #201515;
    color: #fff;
  }
  
  td:hover {
    box-shadow: 0 0 15px #08daff;
  }
  .info {
    padding-top: 5px;
    
  
  }
  .filter {
    display: flex;
    flex-direction: column;
    justify-content: center;
  }
  .grad{
    color: rgb(255, 255, 255);
    font: bold;
    margin: auto;
    padding: 20px; /* Add padding as needed */
    text-align: center;
    text-shadow: #08daff;
    display: inline-block; /* Ensures that the background covers the entire width */
  

  }
  .content {
    z-index: -1;
  }
  select {
    width: 110px;
    border: 2px solid transparent;
    border-radius: 5px;
  }
  select:hover {
    border-color: #3498db;
  }
  button {
    width: 80px;
    border-radius: 5px;
    border: 2px solid transparent;
    transition: border-color 0.3s ease; /* Add transition for smooth animation */
    cursor: pointer;
    background-color: #3498db;
    box-shadow: 0 0 5px #3498db;
    color: white;
  }
  
  button:hover {
    border-color: #3498db;
    transform: scale(1.1);
    
  }
  
  @keyframes bounce {
    0%,
    20%,
    50%,
    80%,
    100% {
      transform: translateY(0); /* Bounce height */
    }
    40% {
      transform: translateY(-10px); /* Bounce height at its peak */
    }
    60% {
      transform: translateY(-5px); /* Bounce height during the bounce */
    }
  }
  label {
    margin: 5px;
    background-color: #fff;
    border-radius: 5px;
  }
  select {
    margin: 5px;
  }
  .operations {
    padding-top: 5px;
    border: #333 solid 2px;
    height: 90px;
    border-radius: 5px;
    display: flex;
    flex-direction: row;
  
    gap: 40px;
    z-index: -1;
    box-shadow: 0 0 15px #3498db;
  }
  .b2 {
    background: url("../assets/stars.jpg");
  }
  .container {
    margin-top: 3px;
    width: 3000px;
    border: rgb(255, 255, 255) solid 3px;
    height: 100vh;
    z-index: -1;
    border-radius: 25px;
    background: url("../assets/back.jpg");
    padding: 20px;
  }
  .header {
    background-color: #4a4a4a;
    margin-top: 0;
    top: 0;
    height: 60px;
    display: flex;
    flex-direction: row;
    padding-left: 100px;
  }
  * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: "Poppins", sans-serif;
  }
  .wrapper {
    height: 100%;
    width: 300px;
    position: relative;
  }
  .wrapper .menu-btn {
    position: absolute;
    left: 20px;
    top: 10px;
    background: #4a4a4a;
    color: #fff;
    height: 45px;
    width: 45px;
    z-index: 9999;
    border: 1px solid #333;
    border-radius: 5px;
    cursor: pointer;
    display: flex;
    align-items: center;
    justify-content: center;
    transition: all 0.3s ease;
  }
  #btn:checked ~ .menu-btn {
    left: 247px;
  }
  .wrapper .menu-btn i {
    position: absolute;
    transform: "";
    font-size: 23px;
    transition: all 0.3s ease;
  }
  .wrapper .menu-btn i.fa-times {
    opacity: 0;
  }
  #btn:checked ~ .menu-btn i.fa-times {
    opacity: 1;
    transform: rotate(-180deg);
  }
  #btn:checked ~ .menu-btn i.fa-bars {
    opacity: 0;
    transform: rotate(180deg);
  }
  #sidebar {
    position: fixed;
    background: #404040;
    height: 100%;
    width: 270px;
    overflow: hidden;
    left: -270px;
    transition: all 0.3s ease;
  }
  #btn:checked ~ #sidebar {
    left: 0;
  }
  #sidebar .title {
    line-height: 65px;
    text-align: center;
    background: #333;
    font-size: 25px;
    font-weight: 600;
    color: #f2f2f2;
    border-bottom: 1px solid #222;
  }
  #sidebar .list-items {
    position: relative;
    background: #404040;
    width: 100%;
    height: 100%;
    list-style: none;
  }
  #sidebar .list-items li {
    padding-left: 40px;
    line-height: 50px;
    border-top: 1px solid rgba(255, 255, 255, 0.1);
    border-bottom: 1px solid #333;
    transition: all 0.3s ease;
  }
  #sidebar .list-items li:hover {
    border-top: 1px solid transparent;
    border-bottom: 1px solid transparent;
    box-shadow: 0 0px 10px 3px #222;
  }
  #sidebar .list-items li:first-child {
    border-top: none;
  }
  #sidebar .list-items li a {
    color: #f2f2f2;
    text-decoration: none;
    font-size: 18px;
    font-weight: 500;
    height: 100%;
    width: 100%;
    display: block;
  }
  #sidebar .list-items li a i {
    margin-right: 20px;
  }
  #sidebar .list-items .icons {
    width: 100%;
    height: 40px;
    text-align: center;
    position: absolute;
    bottom: 100px;
    line-height: 40px;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  #sidebar .list-items .icons a {
    height: 100%;
    width: 40px;
    display: block;
    margin: 0 5px;
    font-size: 18px;
    color: #f2f2f2;
    background: #4a4a4a;
    border-radius: 5px;
    border: 1px solid #383838;
    transition: all 0.3s ease;
  }
  #sidebar .list-items .icons a:hover {
    background: #404040;
  }
  .list-items .icons a:first-child {
    margin-left: 0px;
  }
  .content .header {
    font-size: 45px;
    font-weight: 700;
  }
  .content p {
    font-size: 40px;
    font-weight: 700;
  }
  </style>
  
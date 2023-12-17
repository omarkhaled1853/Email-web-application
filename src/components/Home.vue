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
            <input v-model="search" style="margin-left:5px;" placeholder=" search...." for="searchtypeid">
            <div class="search">
              <select v-model="searchby" id="searchtypeid" name="searchtype">
                <option>Search by</option>
                <option>Date</option>
                <option>Sender</option>
                <option>Receviers</option>
                <option>Importancy</option>
                <option>Subject</option>
                <option>Body</option>
                <option>Attachments</option>
              </select>
              <button @click="srch()" >Search</button>
            </div>
          </div>
          <div class="filter">
            <div class="sort">
              <select v-model="sortby" id="sortid" name="sort">
                <option>Sort by</option>
                <option>Default</option>
                <option>Sender</option>
                <option>Receviers</option>
                <option>Importancy</option>
                <option>Subject</option>
                <option>Body</option>
                <option>Attachments</option>
              </select>
              <button @click="sort()">sort</button>
            </div>
          </div>

          <div class="filter">
            <div class="select">
              <select v-model="filterby" id="filterid" name="filter">
                <option>Filter by</option>
                <option>sender</option>
                <option>Subject</option>
              </select>
              <button @click="filter()">filter</button>
            </div>
          </div>
          <button class="new" @click="delet()">Delete the selected <i class="fa-solid fa-trash" style="font-size:25px; color:red"></i></button>
          <button class="new" @click="dia()">new massage</button>
        </div>
        <v-dialog v-model="dialog" width="800" heigth="850"  dark hide-overlay persistent>
          <v-card>
            <v-card-title style="color:white; background-color:#3498db; padding:auto; text-align:center; font-size:35px">new massage</v-card-title>
            <v-card-text style="background-color:black">
              <v-form>
                <label style="font-size:20px; font:bold; color:#3498db;background-color:black">To:</label>
                <br>
                <input v-model="massage.to" style="width:600px" type="text" placeholder=" user@example.com"  id="toid">
                <br>
                <label style="font-size:20px; font:bold; color:#3498db;background-color:black">From:</label>
                <br>
                <input v-model="massage.from" style="width:600px" type="text" placeholder=" useremail" :value="email"  disabled>
                <br>
                <label style="font-size:20px; font:bold; color:#3498db;background-color:black">Subject:</label>
                <br>
                <input v-model="massage.Subject" style="width:600px" type="text" placeholder=" (0-30)characters">
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
         <!-- // ///////////////////////////////////////////////////////-->
        <v-dialog v-model="folder" width="600" heigth="600"  dark hide-overlay persistent>
          <v-card>
            <v-card-title style="color:white; background-color:#3498db; padding:auto; text-align:center; font-size:35px">Folders<i style="font-size:50px; margin-left:5px;" class="fa-solid fa-folder-plus"> </i></v-card-title>
            <v-card-text style="background-color:black">
              <v-form>
                <label style="font-size:20px; font:bold; color:#3498db;background-color:black" for="menu">select from folders:</label>
                <select id="menu" name="menu" v-model="foldername">
                  <option value="Social">Social</option>
                  <option value="Work">Work</option>
                  <option value="Family">Family</option>
                </select>
                <v-btn style="width:auto; margin-left:20px" @click="addtofolder()">Add To Folder</v-btn>
                <label style="font-size:20px; font:bold; color:#3498db;background-color:black">creat new:</label>
                <br>
                <input v-model="newfoldername" style="width:400px" type="text"  placeholder="new folder name">
                <v-btn style="width:auto;margin-top:15px; margin-left:10px" @click="createnew()">create and add to folder</v-btn>
                </v-form>
            </v-card-text>

            <v-card-actions style="background-color:black">
             
              <v-btn style="width:auto; margin-left:500px" @click="closefolder()">close</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
        <!-- // ///////////////////////////////////////////////////////-->
        <v-dialog v-model="contacts" width="600" heigth="600"  dark hide-overlay persistent>
          <v-card>
            <v-card-title style="color:white; background-color:#3498db; padding:auto; text-align:center; font-size:35px">Contacts<i style="font-size:50px; margin-left:5px;" class="fa-solid fa-user-plus"></i>  </v-card-title>
            <v-card-text style="background-color:black">
              <v-form>
                <label style="font-size:20px; font:bold; color:#3498db;background-color:black">name:</label>
                <input v-model="newname" type="text">
                <br>
                <label style="font-size:20px; font:bold; color:#3498db;background-color:black">email:</label>
                <input :value="this.emails[this.itemindex].Sender" style="width:400px" type="text"   disabled>
                <v-btn style="width:auto;margin-top:15px; margin-left:10px" @click="addtocontacte()">add to contacts</v-btn>
                </v-form>
            </v-card-text>
            <v-card-actions style="background-color:black">
              <v-btn style="width:auto; margin-left:500px" @click="closecontact()">close</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
        <!-- ////////////////////////////////////////////////////////////////////////////// -->
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
         <table border="2px">
          <tr>
            <td>       </td>
            <td>priority</td>
            <td>Sender</td>
            <td>Date</td>
            <td> Subject</td>
            <td>Content</td>
            <td>Attachments</td>
            <td>Move to trash <i class="fa-solid fa-trash" style="font-size:15px; color:red"></i></td>
           
          </tr>
          <tr v-for="item in emails" :key="item.email">
            <td> <input v-model="choosen" :value="item.index" type="checkbox">   <i @click="folddialog(item.index)" class="fa-solid fa-folder-plus"> </i> <i @click="contactdialog(item.index)" class="fa-solid fa-user-plus"></i>   </td>
            <td>{{item.priority}}</td>
            <td>{{item.Sender}}</td>
            <td>{{item.Date}}</td>
            <td> {{item.Subject}}</td>
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
import "@fortawesome/fontawesome-free/css/all.css"; // Import the styles
export default {
  name: "HoMe",
  data() {
    return {
      user_name: "mohamed hassan",
      email: "mohamed@gmail.com",
      dialog:false,
      search: '',
      searchby:'',
      sortby:'',
      filterby:'',
      choosen:[],
      folder:false,
      foldername:'',
      itemindex:'',
      newfoldername:'',
      contacts:false,
      newname:'',
      sittingdialog:false,
      massage:{
        from:'mohamed@gmail.com',
        to:'',
        Subject:'',
        content:'',
        priority:'',
        attachments:'',
      },
      emails: [
        {
          index:'0',
          priority:5,
          Sender:'mohamed@test.com',
          Date:'14/12/2023 11:58',
          Subject:'test',
          Content:'test Home ',
          attachments:'https://www.youtube.com/watch?v=QgA4ZVhoge0'
          
        },
        {
          index:'1',
          priority:2,
          Sender:'omar@test.com',
          Date:'12/12/2023 11:58',
          Subject:'test',
          Content:'test Home ',
          attachments:'no attachments'
        },
        {
          index:'2',
          priority:3,
          Sender:'medo@test.com',
          Date:'18/12/2023 11:58',
          Subject:'test',
          Content:'test Home ',
          attachments:'no attachments'
        },
        {
          index:'3',
          priority:1,
          Sender:'mahmoud@test.com',
          Date:'18/12/2023 11:58',
          Subject:'test',
          Content:'test Home ',
          attachments:'no attachments'
        }
      ],
    };
  },
  // mounted() {
  //   this.user_name = JSON.parse(localStorage.getItem("person-inf")).userName
  //   this.email = JSON.parse(localStorage.getItem("person-inf")).email;
  //   let res = fetch("http://localhost:8080/GetMails", {
  //     method: "GET",
  //   });
  //   this.emails = res.data;
  // },
  methods: {
    dia(){
      this.dialog=!this.dialog
    },
    folddialog(ind){
      this.folder=true
      this.itemindex=ind
    },
    closefolder(){
      this.folder=false
    },
    contactdialog(ind){
      this.contacts=true
      this.itemindex=ind
    },
    closecontact(){
      this.contacts=false
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
    }
    ,
   async draft(){
        this.dialog=false
        await fetch("http://localhost:8080/draft",{
          method:"POST",
          body:JSON.stringify(this.massage)
        }).catch((error)=>{
          console.log("Error",error)
        });
    },
   async send(){
      localStorage.setItem("massage",JSON.stringify(this.massage))
      console.log(`File  name:`, this.massage.attachments.name);
      for (let i = 0; i < this.massage.attachments.length; i++) {
      console.log(`File ${i + 1} name:`, this.massage.attachments[i].name);
      console.log(`File ${i + 1} size:`, this.massage.attachments[i].size);
      // Add other properties as needed
}
      this.dialog=false
      await fetch("http://localhost:8080:send",{
        method:"POST",
        body:JSON.stringify(this.massage)
      })

    },
    handleFileChange(event) {
      console.log(event.target.files)
      this.massage.attachments = event.target.files;
    },
   async srch(){
     let res=  await fetch(`http://localhost:8080/    ?searchby=${this.searchby},search=${this.search}`,{
        method:"GET"
   })
    this.emails=res.data
    },
    async filter(){
      let res =await fetch(`http://localhost:8080/    ?filterby=${this.filterby}`,{
        method:"GET"
   })
    this.emails=res.data
    },
    async sort(){
     let res= await fetch(`http://localhost:8080/    ?sortby=${this.sortby}`,{
        method:"GET"
     })
      this.emails=res.data
    },
   async trash(ind){
      console.log("size "+this.choosen.length)
      console.log(this.choosen[2])
      console.warn(JSON.stringify(this.emails[ind]));
      fetch("http://localhost:8080/trash",{
        method:"POST",
        body:JSON.stringify(this.emails[ind])
      });
      let res= await fetch(`http://localhost:8080/ ?index=${ind}`,{
        method:"DELETE"
     })
      this.emails=res.data


    },
    async delet() {
      if(this.choosen.length===0)
      {
        alert("please select email ")
      }
      else{
      await fetch("http://localhost:8080/trash",{
        method:"POST",
        body:this.choosen
      })
      let res= await fetch(`http://localhost:8080/ ?indcies=${this.choosen}`,{
        method:"DELETE"
     })
      this.emails=res.data

    }
    },
    async addtofolder(){
      await fetch("http://localhost:8080/  ",
      {
        method:"POST",
        body:(this.emails[this.itemindex],this.foldername)
      })
    },
   async createnew(){
      await fetch("http://localhost:8080/  ",
      {
        method:"POST",
        body:(this.emails[this.itemindex],this.newfoldername)
      })

    },
   async addtocontacte(){
      await fetch("http://localhost:8080/  ",
      {
        method:"POST",
        body:(this.emails[this.itemindex],this.newname)
      })
    }
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
  height: 60px;
  margin-top: 15px;
  margin-left: 100px;
  background-color:#3498db; /* Green background color */
  color: white; /* White text color */
  text-align: center; /* Center text */
  font-size: 16px; /* Set font size */
  cursor: pointer; /* Add a pointer cursor on hover */
  transition: transform 0.3s ease-in-out; /* Add transition for scaling effect */
}

/* Add hover effect to scale the button */
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

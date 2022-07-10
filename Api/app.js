const express = require('express'); 
const mongoose = require("mongoose");
const userRoutes= require("./routes/user");
const MedicamentoRoutes = require("./routes/medicamaneto");
const ExamenRoute = require("./routes/examen");
const citaRoute = require("./routes/cita");
const MONGODB_URI = "mongodb+srv://root:root@healthypocketdb.6k5ycz0.mongodb.net/healthypocketdb?retryWrites=true&w=majority";
require("dotenv").config({path: './.env'});
const bodyParser = require('body-parser');
const { request } = require('http');
const { response } = require('express');
const { url } = require('inspector');
const path = require('path');
const bcrypt=require('bcrypt');


const app = express();
const port = process.env.PORT || 9000;


app.use(express.json());
//middleware
app.use('/api',userRoutes);
app.use('/api',MedicamentoRoutes);
app.use('/api',ExamenRoute);
app.use('/api',citaRoute);
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended:true}));
app.use(express.static(path.join(__dirname,'public')));


//routes
app.get('/',(req,res)=> {
    res.send("welcome to my api")
});


//mongodb conecction
mongoose.connect(
    MONGODB_URI
).then(
    ()=> console.log("estas conectado a la BD papasito :D ")
).catch((error)=> console.error(error))


app.listen(port,()=>console.log('server listening in port',port));
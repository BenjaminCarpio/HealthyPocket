const express = require("express");
const router = express.Router();
const citaSchema=require("../models/Entities/cita");


//creando cita
router.post('/cita', (req,res)=> {
    const cita = citaSchema(req.body);
    cita.save().then((data)=>res.json(data)).catch((error)=>res.json({message:error}));
});

//obtener todas las citas
router.get('/cita', (req,res)=> {
    citaSchema.find().then((data)=>res.json(data)).catch((error)=>res.json({message:error}));
});

//obtener cita por id
router.get('/cita/:id', (req,res)=> {
    const{ id }=req.params;
    citaSchema.findById(id).then((data)=>res.json(data)).catch((error)=>res.json({message:error}));
});


//actualizar una cita
router.put('/cita/:id', (req,res)=> {
    const{ id }=req.params;
    const{medico,incapacidad,descripcion,fecha,tiempohospital,cilinahospital,gastos}=req.body
    citaSchema.updateOne({_id:id},{$set: {medico,incapacidad,descripcion,fecha,tiempohospital,cilinahospital,gastos}}).then((data)=>res.json(data)).catch((error)=>res.json({message:error}));
});


//Eliminar cita por Id
router.delete('/cita/:id', (req,res)=> {
    const{ id }=req.params;
    citaSchema.deleteOne({_id:id}).then((data)=>res.json(data)).catch((error)=>res.json({message:error}));
});



module.exports =  router;


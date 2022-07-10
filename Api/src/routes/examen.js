const express = require("express");
const router = express.Router();
const examenSchema=require("../models/Entities/examen");


//creando examen
router.post('/examen', (req,res)=> {
    const examen = examenSchema(req.body);
    examen.save().then((data)=>res.json(data)).catch((error)=>res.json({message:error}));
});

//obtener todos los examenes
router.get('/examen', (req,res)=> {
    examenSchema.find().then((data)=>res.json(data)).catch((error)=>res.json({message:error}));
});

//obtener examen por id
router.get('/examen/:id', (req,res)=> {
    const{ id }=req.params;
    examenSchema.findById(id).then((data)=>res.json(data)).catch((error)=>res.json({message:error}));
});


//Actualizar examen method
router.put('/examen/:id', (req,res)=> {
    const{ id }=req.params;
    const{nombreexamen,gastosexamen,descripcion}=req.body
    examenSchema.updateOne({_id:id},{$set: {nombreexamen,gastosexamen,descripcion}}).then((data)=>res.json(data)).catch((error)=>res.json({message:error}));
});


//Eliminar Examen
router.delete('/examen/:id', (req,res)=> {
    const{ id }=req.params;
    examenSchema.deleteOne({_id:id}).then((data)=>res.json(data)).catch((error)=>res.json({message:error}));
});


module.exports =  router;


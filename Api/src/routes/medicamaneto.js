const express = require("express");
const router = express.Router();
const medicamentoSchema=require("../models/Entities/medicamentos");


//creando Medicamento
router.post('/medicamento', (req,res)=> {
    const medicamento = medicamentoSchema(req.body);
    medicamento.save().then((data)=>res.json(data)).catch((error)=>res.json({message:error}));
});

//obtener Todos los Medicamentos
router.get('/medicamento', (req,res)=> {
    medicamentoSchema.find().then((data)=>res.json(data)).catch((error)=>res.json({message:error}));
});

//obtener Medicamento por Id
router.get('/medicamento/:id', (req,res)=> {
    const{ id }=req.params;
    medicamentoSchema.findById(id).then((data)=>res.json(data)).catch((error)=>res.json({message:error}));
});


//Actualizando Medicamento
router.put('/medicamento/:id', (req,res)=> {
    const{ id }=req.params;
    const{nombremedicamento,horamedicamento}=req.body
    medicamentoSchema.updateOne({_id:id},{$set: {nombremedicamento,horamedicamento}}).then((data)=>res.json(data)).catch((error)=>res.json({message:error}));
});


//Eliminar Medicamento
router.delete('/medicamento/:id', (req,res)=> {
    const{ id }=req.params;
    medicamentoSchema.deleteOne({_id:id}).then((data)=>res.json(data)).catch((error)=>res.json({message:error}));
});



module.exports =  router;


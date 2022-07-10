const mongoose = require("mongoose");

const medicamentoSchema=mongoose.Schema({

    nombremedicamento:{
        type: String,
        required: true
    },
    horamedicamento:{
        type: String,
        required: true
    },
});

module.exports = mongoose.model('Medicamento',medicamentoSchema);
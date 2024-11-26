package com.example.calculadoraev

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import com.example.calculadoraev.databinding.ActivityMainBinding
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan

class MainActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var pizarra: String = ""
    private var op1: Double = 0.0
    private var operador: String = "vacio"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Asociar botones a eventos de clic
        binding.BtnCero.setOnClickListener(this)
        binding.BtnUno.setOnClickListener(this)
        binding.BtnDos.setOnClickListener(this)
        binding.BtnTres.setOnClickListener(this)
        binding.BtnCuatro.setOnClickListener(this)
        binding.BtnCinco.setOnClickListener(this)
        binding.BtnSeis.setOnClickListener(this)
        binding.BtnSiete.setOnClickListener(this)
        binding.Btnocho.setOnClickListener(this)
        binding.BtnNueve.setOnClickListener(this)
        binding.BtnBorrar.setOnClickListener(this)
        binding.BtnCambioSigno.setOnClickListener(this)
        binding.BtnMultiplicar.setOnClickListener(this)
        binding.BtnDivision.setOnClickListener(this)
        binding.BtnPorcentaje.setOnClickListener(this)
        binding.BtnMenos.setOnClickListener(this)
        binding.BtnSumar.setOnClickListener(this)
        binding.BtnIgual.setOnClickListener(this)
        binding.BtnSeno?.setOnClickListener(this)
        binding.Btntangente?.setOnClickListener(this)
        binding.BtnCoseno?.setOnClickListener(this)
        binding.BtnRaizCuadrada?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            binding.BtnBorrar.id -> {
                pizarra = ""
                operador = "vacio"
                binding.Pizarra.text = pizarra
            }
            binding.BtnCero.id -> { pizarra += "0"; binding.Pizarra.text = pizarra }
            binding.BtnUno.id -> { pizarra += "1"; binding.Pizarra.text = pizarra }
            binding.BtnDos.id -> { pizarra += "2"; binding.Pizarra.text = pizarra }
            binding.BtnTres.id -> { pizarra += "3"; binding.Pizarra.text = pizarra }
            binding.BtnCuatro.id -> { pizarra += "4"; binding.Pizarra.text = pizarra }
            binding.BtnCinco.id -> { pizarra += "5"; binding.Pizarra.text = pizarra }
            binding.BtnSeis.id -> { pizarra += "6"; binding.Pizarra.text = pizarra }
            binding.BtnSiete.id -> { pizarra += "7"; binding.Pizarra.text = pizarra }
            binding.Btnocho.id -> { pizarra += "8"; binding.Pizarra.text = pizarra }
            binding.BtnNueve.id -> { pizarra += "9"; binding.Pizarra.text = pizarra }

            binding.BtnCambioSigno.id -> {
                if (pizarra.isNotEmpty()) {
                    pizarra = if (pizarra.startsWith("-")) pizarra.substring(1) else "-$pizarra"
                    binding.Pizarra.text = pizarra
                }
            }

            binding.BtnSumar.id -> setOperator("+")
            binding.BtnMenos.id -> setOperator("-")
            binding.BtnMultiplicar.id -> setOperator("*")
            binding.BtnDivision.id -> setOperator("/")
            binding.BtnPorcentaje.id -> setOperator("%")

            binding.BtnCoseno?.id -> setTrigOperator("Cos")
            binding.BtnSeno?.id -> setTrigOperator("Sen")
            binding.Btntangente?.id -> setTrigOperator("Tg")
            binding.BtnRaizCuadrada?.id -> setTrigOperator("√")
            //creo la funcion para encapsular codigo y que sea mas legible
            binding.BtnIgual.id -> calcularResultado()
        }
    }
//
    private fun setOperator(op: String) {
        if (pizarra.isNotEmpty()) {
            op1 = pizarra.toDouble()
            operador = op
            pizarra = ""
            binding.Pizarra.text = ""
        }
    }

    private fun setTrigOperator(op: String) {
        if (pizarra.isNotEmpty()) {
            op1 = pizarra.toDouble()
            operador = op
            binding.Pizarra.text = op
        }
    }

    private fun calcularResultado() {
        try {
            val result = when (operador) {
                "Cos" -> cos(Math.toRadians(op1))
                "Sen" -> sin(Math.toRadians(op1))
                "Tg" -> tan(Math.toRadians(op1))
                "√" -> if (op1 >= 0) sqrt(op1) else throw IllegalArgumentException("Error: Raíz de número negativo")
                "+" -> op1 + pizarra.toDouble()
                "-" -> op1 - pizarra.toDouble()
                "*" -> op1 * pizarra.toDouble()
                "/" -> {
                    val op2 = pizarra.toDouble()
                    if (op2 != 0.0) op1 / op2 else throw IllegalArgumentException("Error: División por 0")
                }
                "%" -> op1 * pizarra.toDouble() / 100
                else -> throw IllegalArgumentException("Operación inválida")
            }
            binding.Pizarra.text = result.toString()
            operador = "vacio"
            pizarra = ""
        } catch (e: Exception) {
            binding.Pizarra.text = e.message
            operador = "vacio"
            pizarra = ""
        }
    }
}
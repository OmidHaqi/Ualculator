package ir.umut.ualculator

import ir.umut.ualculator.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.Toast
import net.objecthunter.exp4j.ExpressionBuilder


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onNumberClicked()
        onOperatorClicked()


    }

    private fun onOperatorClicked() {

        binding.positive.setOnClickListener {
            if (binding.inputNum.text.isNotEmpty()) {
                val myChar = binding.inputNum.text.last()
                if (
                    myChar != '+' &&
                    myChar != '-' &&
                    myChar != '*' &&
                    myChar != '/'
                ) {

                    appendText("+")

                }
            }

        }
        binding.negative.setOnClickListener {
            if (binding.inputNum.text.isNotEmpty()) {
                val myChar = binding.inputNum.text.last()
                if (
                    myChar != '+' &&
                    myChar != '-' &&
                    myChar != '*' &&
                    myChar != '/'
                ) {

                    appendText("-")

                }
            }
        }
        binding.multiply.setOnClickListener {
            if (binding.inputNum.text.isNotEmpty()) {
                val myChar = binding.inputNum.text.last()
                if (
                    myChar != '+' &&
                    myChar != '-' &&
                    myChar != '*' &&
                    myChar != '/'
                ) {

                    appendText("*")

                }
            }
        }
        binding.division.setOnClickListener {
            if (binding.inputNum.text.isNotEmpty()) {
                val myChar = binding.inputNum.text.last()
                if (
                    myChar != '+' &&
                    myChar != '-' &&
                    myChar != '*' &&
                    myChar != '/'
                ) {

                    appendText("/")

                }
            }
        }
        binding.openParenthesis.setOnClickListener {
            appendText("(")
        }
        binding.closParenthesis.setOnClickListener {
            appendText(")")
        }

        binding.allClear.setOnClickListener {
            binding.inputNum.text = ""
            binding.outputNum.text = ""
        }

        binding.clear.setOnClickListener {
            val oldText = binding.inputNum.text.toString()

            if (oldText.isNotEmpty()) {

                binding.inputNum.text = oldText.substring(0, oldText.length - 1)

            }

        }
        binding.equal.setOnClickListener {
            try {
                val exception = ExpressionBuilder(binding.inputNum.text.toString()).build()
                val result = exception.evaluate()
                val  longResult= result.toLong()
                //570.0 == 570
                if (result==longResult.toDouble()){
                    binding.outputNum.text = longResult.toString()
                }else{
                    binding.outputNum.text=result.toString()
                }
            }catch (e: Exception) {

                binding.inputNum.text = ""
                binding.outputNum.text = ""
                Toast.makeText(this, "What are you doing?!", Toast.LENGTH_LONG).show()

            }



        }

    }

    private fun onNumberClicked() {
        binding.zero.setOnClickListener {
            if (binding.inputNum.text.isNotEmpty()){
                appendText("0")
            }

        }
        binding.one.setOnClickListener {
            appendText("1")
        }
        binding.two.setOnClickListener {
            appendText("2")
        }
        binding.three.setOnClickListener {
            appendText("3")
        }
        binding.four.setOnClickListener {
            appendText("4")
        }
        binding.five.setOnClickListener {
            appendText("5")
        }
        binding.six.setOnClickListener {
            appendText("6")
        }
        binding.seven.setOnClickListener {
            appendText("7")
        }
        binding.eight.setOnClickListener {
            appendText("8")
        }
        binding.nine.setOnClickListener {
            appendText("9")
        }
        binding.dot.setOnClickListener {
            if (binding.inputNum.text.isEmpty() || binding.outputNum.text.isNotEmpty()) appendText("0.")
            else if (binding.inputNum.text.last() != '.') {
                var countOperators = 0
                var countDots = 0
                binding.inputNum.text.forEach {
                    if (it == '+'
                        || it == '-'
                        || it == '*'
                        || it == '/'){
                        countOperators++
                    }
                    if (it == '.'){
                        countDots++
                    }
                }
                if (countDots <= countOperators){
                    appendText(".")
                }
            }

        }
    }

    private fun appendText(newText: String) {

         if (binding.outputNum.text.isNotEmpty()){
             binding.inputNum.text= ""
         }

        binding.outputNum.text=""
        binding.inputNum.append(newText)

        val viewTree: ViewTreeObserver = binding.horizontalScrollViewInputNum.viewTreeObserver
        viewTree.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.horizontalScrollViewInputNum.viewTreeObserver.removeOnGlobalLayoutListener(
                    this
                )
                binding.horizontalScrollViewInputNum.scrollTo(binding.inputNum.width, 0)
            }
        })


    }
}
package bots

//import com.vdurmont.emoji.EmojiParser
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendDocument
import org.telegram.telegrambots.meta.api.methods.send.SendMessage


import org.telegram.telegrambots.meta.api.objects.InputFile
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import java.io.File


class WCCBot : TelegramLongPollingBot() {


    override fun getBotUsername(): String {
        //return bot username
        // If bot username is @HelloKotlinBot, it must return
        return "HelloKotlinBot"
    }

    override fun getBotToken(): String {
        // Return bot token from BotFather
        return "2060314159:AAEFdZ8_XOhF5J3XQfdBjFJ3u-1NCszKUVY"
    }

    override fun onUpdateReceived(update: Update?) {
        // We check if the update has a message and the message has text
        val nameSender = update?.message?.from?.firstName
        val chatId = update?.message?.chatId.toString()
        val messageCommand = update?.message?.text

        try {
            if( messageCommand == "/start"){
            val start = SendMessage().apply{
                this.chatId = chatId
                this.text = welcome(nameSender)
                this.parseMode = "MarkdownV2"
            }
            execute(start)
        }
        else {
            val sendDocument = sender(update)
            execute(sendDocument)

            }
        } catch (e: TelegramApiException) {
        e.printStackTrace()
        }
  }
    private fun sender(update: Update?) = when(update?.message?.text){
        "/academia" -> academia(update)
        "/superior" -> treinoSuperior(update)
        "/abdomen" -> treinoAbdomen(update)
        "/inferior" -> treinoInferior(update)
        "/info" -> info(update)
        else -> defaultMessage(update)
    }

    private fun welcome(nameSender: String?) = """
        *Olá, $nameSender, tudo bem\?*
        
        \/academia \- Academia virtual
        \/info \- Para saber mais sobre o projeto
    """.trimMargin()

    private fun academia(update: Update?): SendDocument{
        val academia = SendDocument().apply{
            val file = File("src/main/resources/MulherFitness.gif")
            this.chatId = update?.message?.chatId.toString()
            this.document = InputFile().setMedia(file)
            this.caption = comandosAcademia()
        }
        return academia
    }

    private fun treinoSuperior(update: Update?): SendDocument{
        val superior1 = SendDocument().apply {
            val file = File("src/main/resources/superior/superiror.gif")
            this.chatId = update?.message?.chatId.toString()
            this.document = InputFile().setMedia(file)
            this.caption = repeticoes()
        }
        val superior2 = SendDocument().apply {
            val file = File("src/main/resources/superior/superior2.gif")
            this.chatId = update?.message?.chatId.toString()
            this.document = InputFile().setMedia(file)
            this.caption = repeticoes()
        }
        val superior3 = SendDocument().apply {
            val file = File("src/main/resources/superior/superior3.gif")
            this.chatId = update?.message?.chatId.toString()
            this.document = InputFile().setMedia(file)
            this.caption = repeticoes()
        }
        execute(superior1)
        execute(superior2)
        return superior3
    }

    private fun treinoAbdomen(update: Update?): SendDocument{
        val abdomen = SendDocument().apply {
            val file = File("src/main/resources/abdomen/abdomen1.gif")
            this.chatId = update?.message?.chatId.toString()
            this.document = InputFile().setMedia(file)
            this.caption = repeticoes()
        }

        val abdomen2 = SendDocument().apply {
            val file = File("src/main/resources/abdomen/abdomen2.gif")
            this.chatId = update?.message?.chatId.toString()
            this.document = InputFile().setMedia(file)
            this.caption = repeticoes()
        }
        val abdomen3 = SendDocument().apply {
            val file = File("src/main/resources/abdomen/abdomen3.gif")
            this.chatId = update?.message?.chatId.toString()
            this.document = InputFile().setMedia(file)
            this.caption = repeticoes()
        }
        execute(abdomen)
        execute(abdomen2)
        return (abdomen3)
    }

    private fun treinoInferior(update: Update?): SendDocument{
        val inferior = SendDocument().apply {
            val file = File("src/main/resources/inferior/agachamento.gif")
            this.chatId = update?.message?.chatId.toString()
            this.document = InputFile().setMedia(file)
            this.caption = repeticoes()
        }

        val inferior2 = SendDocument().apply {
            val file = File("src/main/resources/inferior/levantamentoLateral.gif")
            this.chatId = update?.message?.chatId.toString()
            this.document = InputFile().setMedia(file)
            this.caption = repeticoes()
        }
        val inferior3 = SendDocument().apply {
            val file = File("src/main/resources/inferior/passada.gif")
            this.chatId = update?.message?.chatId.toString()
            this.document = InputFile().setMedia(file)
            this.caption = repeticoes()
        }
        execute(inferior)
        execute(inferior2)
        return(inferior3)
    }

    private fun info(update: Update?): SendDocument{
    val info = SendDocument().apply{
        val file = File("src/main/resources/bot.gif")
        this.chatId = update?.message?.chatId.toString()
        this.document = InputFile().setMedia(file)
        this.caption = """
        Eu sou um bot ainda muito simples
        
        Por enquanto, faço apenas alguns comando, mas em breve farei mais!
        
        Sou um bot feito através do Projeto Woman Can Code, trilha Kotlin
    """.trimIndent()

    }
    return(info)
    }

    private fun comandosAcademia(): String = """
        Bem vindo a Academia Virtual!
           
        Por ora iremos fazer apenas 3 
        exercícios de cada parte:
           -> superior, abdômen e infeiror
        
        Para ver os exercícios, acesse os comandos:   
        /superior
        /abdomen
        /inferior
        """.trimIndent()

    private fun repeticoes(): String = """
        iniciante: 4 x 15 descanso: 2m
        intermediário: 5 x 20 descanso: 1.30s
        avançado: 6 x 25 descanso: 1m
        
        Descanso de 3m entre os exercícios
    """.trimIndent()

    private fun defaultMessage(update: Update?): SendDocument {
        val sendDocument = SendDocument().apply {
            val file = File("src/main/resources/nyan-cat.gif")
            this.chatId = update?.message?.chatId.toString()
            this.document = InputFile().setMedia(file)
            this.caption = """
                Não entendi...
                
                Utilize os comandos abaixo:
                
                /info
                /academia
            """.trimIndent()
        }
        return (sendDocument)
    }

    }



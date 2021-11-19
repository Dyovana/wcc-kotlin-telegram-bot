package bots

import com.vdurmont.emoji.EmojiParser
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendAudio
import org.telegram.telegrambots.meta.api.methods.send.SendDocument
import org.telegram.telegrambots.meta.api.methods.send.SendMessage


import org.telegram.telegrambots.meta.api.objects.InputFile
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import java.io.File


class WCCBot : TelegramLongPollingBot() {
//    val welcome = """
//        *Olá, tudo bem\?*
//
//        \/start \- começar projeto
//        \/music \-Escutar uma musica
//        \/info \- para saber mais sobre o projeto
//        \/seilá\- blabla
//    """.trimMargin()

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
            if( messageCommand == "/music"){
            val gif = SendDocument().apply{
                this.chatId = chatId
                this.document = InputFile().setMedia(File("C:\\Users\\Dyovana\\Desktop\\BotTelegram\\wcc-kotlin-telegram-bot\\src\\main\\resources\\gatinho-gato.gif"))
                this.caption = EmojiParser.parseToUnicode("Por enquanto tenho só essa rsrs :smile:")
                this.parseMode = "MarkdownV2"
            }
            val sendMusic = SendAudio().apply{
                this.chatId = chatId
                this.audio = InputFile().setMedia(File("C:\\Users\\Dyovana\\Desktop\\BotTelegram\\wcc-kotlin-telegram-bot\\src\\main\\resources\\musica.mp3"))
                this.parseMode = "MarkdownV2"
            }
            execute(gif)
            execute(sendMusic)


        }else if(messageCommand == "/info"){
            val info = SendMessage().apply{
                this.chatId = chatId
                this.text = "Informações aqui"
            }
            execute(info)
        }
        else if(messageCommand == "/fotofofa"){
            val foto = SendDocument().apply{
                this.chatId = chatId
                this.document = InputFile().setMedia(File("C:\\Users\\Dyovana\\Desktop\\BotTelegram\\wcc-kotlin-telegram-bot\\src\\main\\resources\\gatofofo.jpg"))
            }
            execute(foto)
        }

        else {
            val sendDocument = SendDocument().apply {
                this.chatId = chatId
                this.caption = getMessage(messageCommand, nameSender)
                this.document = InputFile().setMedia("https://media.giphy.com/media/SKGo6OYe24EBG/giphy.gif")
                //this.document = InputFile().setMedia("C:\\Users\\Dyovana\\Desktop\\BotTelegram\\wcc-kotlin-telegram-bot\\src\\main\\resources\\gatinho-gato.gif")
                this.parseMode = "MarkdownV2"
            }
        execute(sendDocument)
        }

        } catch (e: TelegramApiException) {
        e.printStackTrace()
        }
  }
        private fun getMessage(command: String?, nameSender: String?) = when(command){
            "/info" -> "Não há informações"
            "/start" -> welcome(nameSender)
            "/music" -> "Escutar uma música"
            else -> aprendendo()
        }

    private fun welcome(nameSender: String?) = """
        *Olá, $nameSender, tudo bem\?*
        
        \/start \- começar projeto
        \/music \-Escutar uma musica
        \/fotofofa\- Foto fofa de gato
        \/info \- para saber mais sobre o projeto
        \/seilá\- blabla
    """.trimMargin()

    private fun aprendendo(): String{
        return EmojiParser.parseToUnicode("""
            Não entendi
            
            Estou aprendendo ainda
             
            Vou reenviar os comandos 
            que eu sei fazer :smile:
            
            \/music \-Escutar uma musica
            \/fotofofa\- Foto fofa de gato
            \/info \- para saber mais sobre o projeto
            \/seilá\- blabla""".trimMargin())


    }


    }



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
            if( messageCommand == "/musica"){
            val gif = SendDocument().apply{
                val file = File("src/main/resources/gatinho-gato.gif")
                this.chatId = chatId
                this.document = InputFile().setMedia(file)
                this.caption = EmojiParser.parseToUnicode("Por enquanto tenho essa rsrs :smile:")
                this.parseMode = "MarkdownV2"
            }
            val sendMusic = SendAudio().apply{
                val file = File("src/main/resources/music/dancing.mp3")
                this.chatId = chatId
                this.audio = InputFile().setMedia(file)
                this.parseMode = "MarkdownV2"
            }
            execute(gif)
            execute(sendMusic)


        }else if(messageCommand == "/info"){
            val info = SendMessage().apply{
                this.chatId = chatId
                this.text = "Esse é um bot ainda simples, mas criado com carinho :) Feito através do Projeto Woman Can Code 6ª edição e com a excelente facilitadora Ste Suzart"
            }
            execute(info)
        }
        else if(messageCommand == "/foto"){
            val foto = SendDocument().apply{
                val file = File("src/main/resources/gatofofo.jpg")
                this.chatId = chatId
                this.document = InputFile().setMedia(file)
            }
            execute(foto)
        }

        else if(messageCommand == "/academia") {
                val academia = SendMessage().apply {
                    this.chatId = chatId
                    this.text = comandosAcademia()
                }
                execute(academia)
            }
        else if(messageCommand == "/superior") {
                val superior = SendDocument().apply {
                    val file = File("src/main/resources/superior/superiror.gif")
                    this.chatId = chatId
                    this.document = InputFile().setMedia(file)
                    this.caption = repeticoes()
                }

                val superior2 = SendDocument().apply {
                    val file = File("src/main/resources/superior/superior2.gif")
                    this.chatId = chatId
                    this.document = InputFile().setMedia(file)
                    this.caption = repeticoes()
                }
                val superior3 = SendDocument().apply {
                    val file = File("src/main/resources/superior/superior3.gif")
                    this.chatId = chatId
                    this.document = InputFile().setMedia(file)
                    this.caption = repeticoes()
                }
                execute(superior)
                execute(superior2)
                execute(superior3)

            }
            else if(messageCommand == "/abdomen") {
                val abdomen = SendDocument().apply {
                    val file = File("src/main/resources/abdomen/abdomen1.gif")
                    this.chatId = chatId
                    this.document = InputFile().setMedia(file)
                    this.caption = repeticoes()
                }

                val abdomen2 = SendDocument().apply {
                    val file = File("src/main/resources/abdomen/abdomen2.gif")
                    this.chatId = chatId
                    this.document = InputFile().setMedia(file)
                    this.caption = repeticoes()
                }
                val abdomen3 = SendDocument().apply {
                    val file = File("src/main/resources/abdomen/abdomen3.gif")
                    this.chatId = chatId
                    this.document = InputFile().setMedia(file)
                    this.caption = repeticoes()
                }
                execute(abdomen)
                execute(abdomen2)
                execute(abdomen3)

            }
            else if(messageCommand == "/inferior") {
                val inferior = SendDocument().apply {
                    val file = File("src/main/resources/inferior/agachamento.gif")
                    this.chatId = chatId
                    this.document = InputFile().setMedia(file)
                    this.caption = repeticoes()
                }

                val inferior2 = SendDocument().apply {
                    val file = File("src/main/resources/inferior/levantamentoLateral.gif")
                    this.chatId = chatId
                    this.document = InputFile().setMedia(file)
                    this.caption = repeticoes()
                }
                val inferior3 = SendDocument().apply {
                    val file = File("src/main/resources/inferior/passada.gif")
                    this.chatId = chatId
                    this.document = InputFile().setMedia(file)
                    this.caption = repeticoes()
                }
                execute(inferior)
                execute(inferior2)
                execute(inferior3)

            }
        else {
            val sendDocument = SendDocument().apply {
                this.chatId = chatId
                this.caption = getMessage(messageCommand, nameSender)
                this.document = InputFile().setMedia(File("src/main/resources/nyan-cat.gif"))
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
            "/musica" -> "Escutar uma música"
            else -> aprendendo()
        }

    private fun welcome(nameSender: String?) = """
        *Olá, $nameSender, tudo bem\?*
        
        \/start \- Começar/ver comandos
        \/musica \- Escutar uma musica
        \/foto\- Alguma foto fofa
        \/academia \- Academia virtual
        \/info \- Para saber mais sobre o projeto
    """.trimMargin()

    private fun aprendendo(): String{
        return EmojiParser.parseToUnicode("""
            Não entendi
            
            Estou aprendendo ainda
             
            Vou reenviar os comandos 
            que eu sei fazer :smile:
            
            \/music \-Escutar uma musica
            \/fotofofa\- Foto fofa de gato
            \/academia \- Academia vitual
            \/info \- Para saber mais sobre o projeto
            """.trimMargin())


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



    }



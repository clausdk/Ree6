package de.presti.ree6.commands.impl.music;

import de.presti.ree6.bot.BotInfo;
import de.presti.ree6.commands.Category;
import de.presti.ree6.commands.interfaces.Command;
import de.presti.ree6.commands.CommandEvent;
import de.presti.ree6.commands.interfaces.ICommand;
import de.presti.ree6.main.Data;
import de.presti.ree6.main.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

import java.awt.*;

@Command(name = "songinfo", description = "Get the current playing Track!", category = Category.MUSIC)
public class SongInfo implements ICommand {

    @Override
    public void onPerform(CommandEvent commandEvent) {

        EmbedBuilder em = new EmbedBuilder();

        em.setAuthor(commandEvent.getGuild().getJDA().getSelfUser().getName(), Data.WEBSITE,
                commandEvent.getGuild().getJDA().getSelfUser().getAvatarUrl());
        em.setTitle("Music Player!");
        em.setThumbnail(commandEvent.getGuild().getJDA().getSelfUser().getAvatarUrl());
        em.setColor(Color.GREEN);
        em.setDescription(Main.getInstance().getMusicWorker().getGuildAudioPlayer(commandEvent.getGuild()).player.getPlayingTrack() == null ? "No Song is being played right now!" : "**Song:** ```"
                + Main.getInstance().getMusicWorker().getGuildAudioPlayer(commandEvent.getGuild()).player.getPlayingTrack().getInfo().title + " by " + Main.getInstance().getMusicWorker().getGuildAudioPlayer(commandEvent.getGuild()).player.getPlayingTrack().getInfo().author + "```");
        em.setFooter(commandEvent.getGuild().getName() + " - " + Data.ADVERTISEMENT, commandEvent.getGuild().getIconUrl());

        Main.getInstance().getCommandManager().sendMessage(em, 5, commandEvent.getTextChannel(), commandEvent.getInteractionHook());
    }

    @Override
    public CommandData getCommandData() {
        return null;
    }

    @Override
    public String[] getAlias() {
        return new String[] { "trackinfo", "cq" };
    }
}
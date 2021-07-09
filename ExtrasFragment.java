package com.simpleapps22.dholuo;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

/**
 * {@link Fragment} that displays a list of family vocabulary words.
 */
public class ExtrasFragment extends Fragment {

    /**
     * Handles playback of all the sound files
     */
    private MediaPlayer mMediaPlayer;

    /**
     * Handles audio focus when playing a sound file
     */
    private AudioManager mAudioManager;

    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    private final AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and up resources
                releaseMediaPlayer();
            }
        }
    };

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private final MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    public ExtrasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        // Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        // Create a list of words
        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("Information", "Lendo",
                R.raw.lendo));
        words.add(new Word("Do", "Timo",
                R.raw.timo));
        words.add(new Word("Do not", "Kik",
                R.raw.kik));
        words.add(new Word("Spare time/ Freedom/ Leisure", "Thuolo",
                R.raw.thuolo));
        words.add(new Word("Sweep, Rest, Lean, Relax, Breath", "Yueyo",
                R.raw.yueyo));
        words.add(new Word("Rest", "Budho",
                R.raw.budho_rest));
        words.add(new Word("Act/ Deed/ Habit (Plural)", "Tim (Timbe)",
                R.raw.tim_timbe));
        words.add(new Word("Look", "Ng'i",
                R.raw.ngi));
        words.add(new Word("See", "Ne",
                R.raw.ne));
        words.add(new Word("Listen", "Chik iti",
                R.raw.chik_iti));
        words.add(new Word("Hear/ Understand/ Feel", "Winjo",
                R.raw.winjo));
        words.add(new Word("Talk/ Speak", "Wuoyo",
                R.raw.wuoyo));
        words.add(new Word("Chat", "Goyo mbaka",
                R.raw.goyo_mbaka));
        words.add(new Word("Call/ Order, Call (On phone)", "Luongo, Goyo simu",
                R.raw.luongo));
        words.add(new Word("Smell", "Ng'weyo",
                R.raw.ngweyo));
        words.add(new Word("Stink, Fly, Jump", "Dum",
                R.raw.dum));
        words.add(new Word("Lick", "Nang'o",
                R.raw.nango));
        words.add(new Word("Suckle", "Dhodho",
                R.raw.dhodho));
        words.add(new Word("Touch", "Mul",
                R.raw.mul));
        words.add(new Word("Laugh", "Nyiero",
                R.raw.nyiero));
        words.add(new Word("Weep", "Yuak",
                R.raw.yuak));
        words.add(new Word("Mourn", "Yuago",
                R.raw.yuago));
        words.add(new Word("Sad", "Kuyo",
                R.raw.kuyo));
        words.add(new Word("Smile", "Buonjo",
                R.raw.buonjo));
        words.add(new Word("Come", "Bii",
                R.raw.bii));
        words.add(new Word("Come in/ Enter", "Donj",
                R.raw.donj));
        words.add(new Word("Go", "Dhiyo",
                R.raw.dhiyo));
        words.add(new Word("Go through", "Kalo",
                R.raw.kalo));
        words.add(new Word("Visit", "Limo",
                R.raw.limo));
        words.add(new Word("Leave/ Come out", "Wuok",
                R.raw.wuok));
        words.add(new Word("Late", "Lewo",
                R.raw.lewo));
        words.add(new Word("Early", "Chon",
                R.raw.chon));
        words.add(new Word("Turn", "Loko",
                R.raw.loko));
        words.add(new Word("Return, Go back, Come back, Reply", "Duok, Dok, Duogo, Duoko",
                R.raw.duok));
        words.add(new Word("Remain", "Dong'",
                R.raw.dong));
        words.add(new Word("Move", "Sudo",
                R.raw.sudo));
        words.add(new Word("Support", "Teno",
                R.raw.teno));
        words.add(new Word("Request", "Kwayo",
                R.raw.kwayo));
        words.add(new Word("Please", "Kiyie",
                R.raw.kiyie));
        words.add(new Word("Try", "Temo",
                R.raw.temo));
        words.add(new Word("Wake", "Chiewo",
                R.raw.chiewo));
        words.add(new Word("Wake up", "Chiew",
                R.raw.chiew));
        words.add(new Word("Give birth", "Nyuolo",
                R.raw.nyuolo));
        words.add(new Word("Thank/ Thanks", "Erokamano",
                R.raw.erokamano));
        words.add(new Word("Care", "Dewo",
                R.raw.dewo));
        words.add(new Word("Help", "Konyo",
                R.raw.konyo));
        words.add(new Word("Rescue", "Reso",
                R.raw.reso));
        words.add(new Word("Greet", "Moso",
                R.raw.moso));
        words.add(new Word("Fetch water", "Omo",
                R.raw.omo));
        words.add(new Word("Dig/ Farm", "Pur/ Puro",
                R.raw.pur_puro));
        words.add(new Word("Plant", "Pidho",
                R.raw.pidho));
        words.add(new Word("Harvest", "Kayo/ Keyo",
                R.raw.kayo_keyo));
        words.add(new Word("Graze cows", "Kwath, Kwayo",
                R.raw.kwath));
        words.add(new Word("Wash", "Luoko",
                R.raw.luoko));
        words.add(new Word("Dry", "Tuoyo",
                R.raw.tuoyo));
        words.add(new Word("Cook", "Tedo",
                R.raw.tedo));
        words.add(new Word("Cook porridge", "Nudo",
                R.raw.nudo));
        words.add(new Word("Fry", "Chielo",
                R.raw.chielo));
        words.add(new Word("Eat", "Cham",
                R.raw.cham));
        words.add(new Word("Drink (Only water), Drink (Anything but water)", "Modho, Madho",
                R.raw.modho));
        words.add(new Word("Jump", "Chikruok",
                R.raw.chikruok));
        words.add(new Word("Walk", "Wuotho",
                R.raw.wuotho));
        words.add(new Word("Stroll", "Bayo",
                R.raw.bayo));
        words.add(new Word("Stop/ Stand", "Chung'",
                R.raw.chung));
        words.add(new Word("Stop/ Cease", "Weyo",
                R.raw.weyo));
        words.add(new Word("Forward, Continue", "Nyime, Dhi nyime",
                R.raw.nyime));
        words.add(new Word("Journey", "Wuoth",
                R.raw.wuoth));
        words.add(new Word("Inform", "Lando",
                R.raw.lando));
        words.add(new Word("Instruct/ Teach", "Puonjo",
                R.raw.puonjo));
        words.add(new Word("Learn", "Puonjre",
                R.raw.puonjre));
        words.add(new Word("Practice", "Puonjruok",
                R.raw.puonjruok));
        words.add(new Word("Dance", "Miel",
                R.raw.miel));
        words.add(new Word("Gossip", "Kuotho",
                R.raw.kuotho));
        words.add(new Word("Sleeping", "Nindo",
                R.raw.nindo));
        words.add(new Word("Work", "Tiyo",
                R.raw.tiyo));
        words.add(new Word("Use", "Tiyogo",
                R.raw.tiyogo));
        words.add(new Word("Run", "Ringo",
                R.raw.ringo));
        words.add(new Word("Ready", "Maoyikore",
                R.raw.maoyikore));
        words.add(new Word("Start/ Begin", "Chako",
                R.raw.chako));
        words.add(new Word("Finish", "Tieko",
                R.raw.tieko));
        words.add(new Word("End", "Giko",
                R.raw.giko));
        words.add(new Word("Hide", "Pondo",
                R.raw.pondo));
        words.add(new Word("Hurry", "Rikni/ Reto",
                R.raw.rikni));
        words.add(new Word("Whistle", "Liyo",
                R.raw.liyo));
        words.add(new Word("Open/ Unlock", "Yawo",
                R.raw.yawo));
        words.add(new Word("Sow", "Komo",
                R.raw.komo));
        words.add(new Word("Know", "Ng'eyo",
                R.raw.ngeyo));
        words.add(new Word("Cough", "Fuolo",
                R.raw.fuolo));
        words.add(new Word("Sneeze", "Gir",
                R.raw.gir_sneeze));
        words.add(new Word("Take", "Kawo",
                R.raw.kawo));
        words.add(new Word("Surround/ Circle", "Luoro",
                R.raw.luoro));
        words.add(new Word("Protect, Wait, Guard", "Rito",
                R.raw.rito));
        words.add(new Word("Keep", "Kano",
                R.raw.kano));
        words.add(new Word("Put", "Keto",
                R.raw.keto));
        words.add(new Word("Lend/ Loan", "Holo",
                R.raw.holo));
        words.add(new Word("Harm", "Hinyo",
                R.raw.hinyo));
        words.add(new Word("Snatch", "Mayo",
                R.raw.mayo));
        words.add(new Word("Throw", "Wito",
                R.raw.wito));
        words.add(new Word("Miss", "Bar",
                R.raw.bar));
        words.add(new Word("Steal", "Kwalo",
                R.raw.kwalo));
        words.add(new Word("Hold", "Mako",
                R.raw.mako));
        words.add(new Word("Give", "Miyo",
                R.raw.miyo));
        words.add(new Word("Bring", "Kel",
                R.raw.kel));
        words.add(new Word("Grow", "Ti, Dongo",
                R.raw.ti_dongo));
        words.add(new Word("Agree, Believe/ Faith", "Yie",
                R.raw.yie));
        words.add(new Word("Yes", "Kamano/ Ee",
                R.raw.kamano_ee));
        words.add(new Word("Reject", "Dagi",
                R.raw.dagi));
        words.add(new Word("Rebel", "Gomo",
                R.raw.gomo));
        words.add(new Word("Refuse", "Tamruok",
                R.raw.tamruok));
        words.add(new Word("No", "Ooyo/ Daa, Ah-ah",
                R.raw.ooyo));
        words.add(new Word("Not", "Ok",
                R.raw.ok_not));
        words.add(new Word("Not yet", "Pok",
                R.raw.pok));
        words.add(new Word("Climb", "Idho",
                R.raw.idho));
        words.add(new Word("Punish", "Sando",
                R.raw.sando));
        words.add(new Word("Insult", "Yanyo",
                R.raw.yanyo));
        words.add(new Word("Rebuke", "Rogo",
                R.raw.rogo));
        words.add(new Word("Yell", "Giyo",
                R.raw.giyo));
        words.add(new Word("Scare away", "Buogo",
                R.raw.buogo));
        words.add(new Word("Chase away/ Drive", "Riembo",
                R.raw.riembo));
        words.add(new Word("Press", "Diyo",
                R.raw.diyo));
        words.add(new Word("Push", "Dhiro",
                R.raw.dhiro));
        words.add(new Word("Pull", "Yuayo",
                R.raw.yuayo));
        words.add(new Word("Beat", "Goyo",
                R.raw.goyo));
        words.add(new Word("Slap", "Thalo/ Pado",
                R.raw.thalo));
        words.add(new Word("Whip", "Chwado",
                R.raw.chwado));
        words.add(new Word("Bite", "Kayo",
                R.raw.kayo));
        words.add(new Word("Strangle", "Deyo",
                R.raw.deyo));
        words.add(new Word("Chop", "Tong'o",
                R.raw.tongo));
        words.add(new Word("Cut/ Divide/ Reduce", "Ng'ado",
                R.raw.ngado));
        words.add(new Word("Slash", "Beto",
                R.raw.beto));
        words.add(new Word("Suicide", "Derruok",
                R.raw.derruok));
        words.add(new Word("Bury", "Yiko",
                R.raw.yiko));
        words.add(new Word("Make/ Fix/ Repair", "Loso",
                R.raw.loso));
        words.add(new Word("Want", "Dwaro",
                R.raw.dwaro));
        words.add(new Word("Take somewhere", "Tero",
                R.raw.tero));
        words.add(new Word("Send", "Oro",
                R.raw.oro));
        words.add(new Word("Guide", "Tayo ng'ato",
                R.raw.tayo_ngato));
        words.add(new Word("Plan", "Chano",
                R.raw.chano));
        words.add(new Word("Search", "Manyo",
                R.raw.manyo));
        words.add(new Word("Examine/ Inspect", "Nono",
                R.raw.nono));
        words.add(new Word("Research", "Nonro",
                R.raw.nonro));
        words.add(new Word("Find", "Yudo",
                R.raw.yudo));
        words.add(new Word("Get", "Gam",
                R.raw.gam));
        words.add(new Word("Gain", "Nueng'o/ Yuto",
                R.raw.nuengo));
        words.add(new Word("Absent", "Onge",
                R.raw.onge));
        words.add(new Word("Present", "Tie",
                R.raw.tie));
        words.add(new Word("Pick", "Kwanyo",
                R.raw.kwanyo));
        words.add(new Word("Remove/ Subtract", "Golo",
                R.raw.golo));
        words.add(new Word("Desire", "Gombo",
                R.raw.gombo));
        words.add(new Word("Kiss", "Nyodho",
                R.raw.nyodho));
        words.add(new Word("Massage", "Ote",
                R.raw.ote));
        words.add(new Word("Adore", "Lamo",
                R.raw.lamo));
        words.add(new Word("Praise", "Pako/ Puoyo",
                R.raw.pako));
        words.add(new Word("Honour", "Luor",
                R.raw.luor));
        words.add(new Word("Like/ Love", "Hero",
                R.raw.hero));
        words.add(new Word("Similar/ Like/ How (Comparison)", "Kaka, Machal/ Machalo",
                R.raw.kaka_machal));
        words.add(new Word("More than", "Moloyo",
                R.raw.moloyo));
        words.add(new Word("Like that", "Kamano",
                R.raw.kamano));
        words.add(new Word("Cover", "Imo",
                R.raw.imo));
        words.add(new Word("Tie", "Tweyo",
                R.raw.tweyo));
        words.add(new Word("Link/ Join", "Tudo",
                R.raw.tudo));
        words.add(new Word("Free/ Zero", "Nono",
                R.raw.nono));
        words.add(new Word("Undress", "Lonyo",
                R.raw.lonyo));
        words.add(new Word("Hang", "Liero",
                R.raw.liero));
        words.add(new Word("Buy", "Ng'iewo",
                R.raw.ngiewo));
        words.add(new Word("Redeem/ Save/ Buy in wholesale", "Waro",
                R.raw.waro));
        words.add(new Word("Bargain", "Goyo nengo",
                R.raw.goyo_nengo));
        words.add(new Word("Pay/ Penance", "Chulo",
                R.raw.chulo));
        words.add(new Word("Sell", "Uso",
                R.raw.uso));
        words.add(new Word("Profit/ Business", "Ohala",
                R.raw.ohala));
        words.add(new Word("Expensive/ Hard/ Difficult", "Matek",
                R.raw.matek));
        words.add(new Word("Easy", "Mayom",
                R.raw.mayom));
        words.add(new Word("Write/ Employ, Draw", "Ndiko, Gorro",
                R.raw.ndiko));
        words.add(new Word("Gather", "Choko/ Ng'ueto",
                R.raw.choko_ngueto));
        words.add(new Word("Mix/ Join/ Unite/ Add to", "Riwo",
                R.raw.riwo));
        words.add(new Word("Select/ Choose/ Elect", "Yiero",
                R.raw.yiero));
        words.add(new Word("Weeding", "Doyo",
                R.raw.doyo));
        words.add(new Word("Busy", "Dich",
                R.raw.dich));
        words.add(new Word("Together", "Kanyakla/ Ka'achiel",
                R.raw.kanyakla_kachiel));
        words.add(new Word("Happen", "Timore",
                R.raw.timore));
        words.add(new Word("Dream", "Leko",
                R.raw.leko));
        words.add(new Word("Worry", "Parruok",
                R.raw.parruok));
        words.add(new Word("Burn, It is burnt", "Wang'o, Owang'",
                R.raw.wango));
        words.add(new Word("Tremble", "Kirni/ Tetni",
                R.raw.kirni));
        words.add(new Word("Victory", "Loch",
                R.raw.loch));
        words.add(new Word("Win/ Overcome", "Loyo",
                R.raw.loyo));
        words.add(new Word("And/ With/ Through/ During, And/ With", "Gi, Kod",
                R.raw.gi_kod));
        words.add(new Word("Of/ About/ For/ On", "Kuom",
                R.raw.kuom));
        words.add(new Word("Of", "Mar",
                R.raw.mar));
        words.add(new Word("If/ When", "Ka",
                R.raw.ka));
        words.add(new Word("For", "Ni/ Mag",
                R.raw.ni_mag));
        words.add(new Word("So that", "Ni mondo/ Mondo",
                R.raw.nimondo));
        words.add(new Word("To", "Ni/ E",
                R.raw.ni_e));
        words.add(new Word("At, During, Is, In", "E",
                R.raw.e));
        words.add(new Word("Still/ Yet", "Pod",
                R.raw.pod));
        words.add(new Word("May/ Might/ Can", "Nyalo",
                R.raw.nyalo));
        words.add(new Word("Maybe", "Nyalo bedo",
                R.raw.nyalo_bedo));
        words.add(new Word("And then", "Kae to",
                R.raw.kae_to));
        words.add(new Word("Also", "Bende",
                R.raw.bende));
        words.add(new Word("Only/ Alone", "Kende",
                R.raw.kende));
        words.add(new Word("Truly", "Adier",
                R.raw.adier));
        words.add(new Word("Exactly", "E wang'e",
                R.raw.e_wange));
        words.add(new Word("Until/ Should/ Must", "Nyaka",
                R.raw.nyaka));
        words.add(new Word("Therefore", "Omiyo",
                R.raw.omiyo));
        words.add(new Word("Should", "Onego, Owinjore",
                R.raw.onego_owinjore));
        words.add(new Word("Because", "Niketch/ Nimar/ Niwach",
                R.raw.nikech_nimar));
        words.add(new Word("Reason", "Gimaomiyo",
                R.raw.gimaomiyo));
        words.add(new Word("Just", "Makare",
                R.raw.makare));
        words.add(new Word("Very", "Hinya",
                R.raw.hinya));
        words.add(new Word("Perhaps", "Kanmoro/ Chalo/ Dibedie/ Dipo/ Koso",
                R.raw.kanmoro));
        words.add(new Word("Almost/ Close", "Chiegni",
                R.raw.chiegni));
        words.add(new Word("Here, There, Over there", "Ka, Kanyo/ Kacha, Kucha/ Kuro.",
                R.raw.ka_kanyo));
        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_family);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView = rootView.findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);

        // Set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();

                // Get the {@link Word} object at the given position the user clicked on
                Word word = words.get(position);

                // Request audio focus so in order to play the audio file. The app needs to play a
                // short audio file, so we will request audio focus with a short amount of time
                // with AUDIOFOCUS_GAIN_TRANSIENT.
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // We have audio focus now.

                    // Create and setup the {@link MediaPlayer} for the audio resource associated
                    // with the current word
                    mMediaPlayer = MediaPlayer.create(getActivity(), word.getAudioResourceId());

                    // Start the audio file
                    mMediaPlayer.start();

                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();

        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
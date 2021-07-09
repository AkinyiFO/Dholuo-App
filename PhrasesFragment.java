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
 * {@link Fragment} that displays a list of phrases.
 */
public class PhrasesFragment extends Fragment {

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
                // Stop playback and clean up resources
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

    public PhrasesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        // Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        // Create a list of words
        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("Greetings", "Mosruok",
                R.raw.mosruok));
        words.add(new Word("Peace be with you! And also with you!", "Misawa! Misawa ahinya!",
                R.raw.misawa));
        words.add(new Word("How do you do? How do you do. (To more than one person)", "Misawa? Misawa. (Osaworeuru? Misawauru ahinya.)",
                R.raw.misawa_how));
        words.add(new Word("Greetings! (Plural) Salutations!", "Amosi! (Amoso u!) Ber ahinya.",
                R.raw.amosi));
        words.add(new Word("Good morning? Good morning to you.", "Oyawore? Oyawore ahinya.",
                R.raw.oyawore));
        words.add(new Word("Good morning to you all? Good morning to you too.", "Oyaworeuru? Oyawore ahinya.",
                R.raw.oyaworeuru));
        words.add(new Word("How did you wake up? I woke up well.", "Ichiew nade? Achiew maber.",
                R.raw.ichiewnade));
        words.add(new Word("How are you? I am well.", "Idhi nadi? Adhi maber. In nadi? An maber. Koro? Ber. Nang'o? Ber ahinya. Intie? Antie.",
                R.raw.idhi_nadi));
        words.add(new Word("Are you well? I am very well.", "In ngima? Angima maber.",
                R.raw.ingima));
        words.add(new Word("Goodday.", "Osawore.",
                R.raw.osawore));
        words.add(new Word("How was your day? It was okay.", "Iriyo nadi? Ariyo maber.",
                R.raw.iriyo_nadi));
        words.add(new Word("Good evening? Good evening. (To more than one person)", "Oyimore? Oyimore. (Oyimoreuru? Oyimore uru ahinya.)",
                R.raw.oyimore));
        words.add(new Word("Goodnight.", "Nind maber.",
                R.raw.nind_maber));
        words.add(new Word("Goodbye. (To more than one person)", "Oriti.(Oriti uru.)",
                R.raw.oriti));
        words.add(new Word("See you.", "Wanere.",
                R.raw.wanere));
        words.add(new Word("I/ I am, We/ We are", "An, Wan",
                R.raw.an_wan));
        words.add(new Word("You/ You are, You all/ You all are", "In, Un",
                R.raw.in_un));
        words.add(new Word("S/he/ S/he is, They/ They are", "En, Gin",
                R.raw.en_gin));
        words.add(new Word("I am a teacher. We are farmers.", "An japuonj. Wan jopur.",
                R.raw.an_japuonj));
        words.add(new Word("I was, We were", "Ne an, Ne wan",
                R.raw.ne_an));
        words.add(new Word("You were/ You all were", "Ne in, Ne un",
                R.raw.ne_in));
        words.add(new Word("S/he was, They were", "Ne en, Ne gin",
                R.raw.ne_en));
        words.add(new Word("You were making noise. You all were playing.", "Ne igoyo koko. Ne utugo.",
                R.raw.ne_igoyo));
        words.add(new Word("I will be/ I will sit, We will be/ We will sit", "Abiro bet, Wabiro bet",
                R.raw.abiro_bet));
        words.add(new Word("You will be/ You will sit, You all will be/ You will all sit", "Ibiro bet, Ubiro bet",
                R.raw.ibiro_bet));
        words.add(new Word("S/he will be/ She will sit, They will be/ They will sit", "Obiro bet, Gibiro bet",
                R.raw.obiro_bet));
        words.add(new Word("She will be a doctor. They will be going to the meeting.", "Obiro bedo ajuoga.  Gibiro ga dhi e bura.",
                R.raw.obiro_bedo));
        words.add(new Word("Mine, Ours (About more than one object)", "Mara, Marwa (Maga, Magwa)",
                R.raw.mara_marwa));
        words.add(new Word("Yours (To more than one person) (About more than one object)", "Mari (Magu) (Magi, Magu)",
                R.raw.mari_magu));
        words.add(new Word("His/ Hers, Theirs (About more than one object)", "Mare, Margi (Mage, Magi)",
                R.raw.mare_margi));
        words.add(new Word("Thing, Things", "Gir, Gik",
                R.raw.gir_gik));
        words.add(new Word("I have three children.", "An gi nyithindo adek.",
                R.raw.an_gi));
        words.add(new Word("I have it, I don't have it", "An go, Aonge go",
                R.raw.an_go));
        words.add(new Word("I have a lot of money.", "An gi pesa mang'eny.",
                R.raw.an_gipesa));
        words.add(new Word("A long time ago.", "Chon gi lala.",
                R.raw.chon_gilala));
        words.add(new Word("One day.", "Chieng' moro.",
                R.raw.chieng_moro));
        words.add(new Word("A cow feeds on grass.", "Dhiang' chamoga lum'.",
                R.raw.dhiang_chamoga));
        words.add(new Word("They have been meeting.", "Ne gisebedo ka giromo.",
                R.raw.ne_gisebedo));
        words.add(new Word("Lend me money.", "Hola pesa.",
                R.raw.hola_pesa));
        words.add(new Word("I don't have money right now, because I am jobless.", "Sani, aonge gi pesa niketch aonge tich.",
                R.raw.sani_aonge));
        words.add(new Word("If you come to my house, I will give you porridge.", "Ka ibiro oda, abo mii nyuka.",
                R.raw.ka_ibiro_oda));
        words.add(new Word("When I go to work, I wear nice clothes.", "Ka adhi tich, arwako nanga maber.",
                R.raw.ka_dhi));
        words.add(new Word("There is a chair here.", "Ntie kom ka.",
                R.raw.ntie_kom));
        words.add(new Word("Please help me.", "Kiyie konya.",
                R.raw.kiyie_konya));
        words.add(new Word("Please show me the way to the market.", "Kiyie to nyisa yoo madhi e chiro.",
                R.raw.kiyie_to));
        words.add(new Word("I am in the house.", "An e ot.",
                R.raw.an_eot));
        words.add(new Word("I am just sitting.", "Abet abeta.",
                R.raw.abet_abeta));
        words.add(new Word("Stand up.", "Chung' malo.",
                R.raw.chung_malo));
        words.add(new Word("Climb up.", "Idh malo.",
                R.raw.idh_malo));
        words.add(new Word("Sit down. (To more than one person)", "Bed piny. (Bed uru piny.)",
                R.raw.bed_piny));
        words.add(new Word("Calm down.", "Lor piny.",
                R.raw.lor_piny));
        words.add(new Word("Wait for me.", "Rita.",
                R.raw.rita));
        words.add(new Word("Thank you for the food.", " Erokamano kuom chiemo.",
                R.raw.erokamano_kuom));
        words.add(new Word("Anything can make you happy.", "Gimoro a mora nyalo mori.",
                R.raw.gimora_nyalo));
        words.add(new Word("The sun rises from the East, and sets in the West.", "Chieng' wuok yor Ugwe, to podho yor Yimbo.",
                R.raw.chieng_wuok));
        words.add(new Word("Rusinga Island is in Lake Victoria.", "Chula Rusinga, nitie Nam Lolwe.",
                R.raw.chula_rusinga));
        words.add(new Word("The waves go towards the south ", "Apaka dhi yor milambo.",
                R.raw.apaka_dhi));
        words.add(new Word("The Nile perch is as sweet as the Tilapia.", "Mbuta mit kaka ngege.",
                R.raw.mbuta_mit));
        words.add(new Word("Honey is sweeter than sugar.", "Mo mar kich mit moloyo sikari.",
                R.raw.mo_mar));
        words.add(new Word("Bring me a cloth that looks like this.", "kelna nanga machalo gi ma.",
                R.raw.kelna_nanga));
        words.add(new Word("A hyena laughs like a human.", "Ondiek nyiero ka ng'ato.",
                R.raw.ondiek_nyiero));
        words.add(new Word("We are going to use that fishing net.", "Gogo no wadhi tiyogo.",
                R.raw.gogo_no));
        words.add(new Word("I am going to cast my vote.", "Adhi goyo mbulu.",
                R.raw.adhi_goyo));
        words.add(new Word("The house of Ogal.", "Od Ogal.",
                R.raw.od_ogal));
        words.add(new Word("The star of the world.", "Sulwe mar piny.",
                R.raw.sulwe_mar));
        words.add(new Word("Onyango usually runs in the morning.", "Onyango ringoga gokiny.",
                R.raw.onyango_ringoga));
        words.add(new Word("We are going to tie a rope on the roof.", "Wadhi tweyo tol e tado.",
                R.raw.wadhi_tweyo));
        words.add(new Word("Akinyi is opening the door.", "Akinyi yawo dhoot.",
                R.raw.akinyi_yawo));
        words.add(new Word("I am going to the lake.", "Adhi e nam/ Adhi nam.",
                R.raw.adhie_nam));
        words.add(new Word("Take me to the lake.", "Tera nam.",
                R.raw.tera_nam));
        words.add(new Word("I live in Kisumu with my mother.", "Adak Kisumu bende gi minwa.",
                R.raw.adak_kisumu));
        words.add(new Word("I would have tried.", "De atemo.",
                R.raw.de_atemo));
        words.add(new Word("I will be going.", "Abiro ga dhi.",
                R.raw.abiroga_dhi));
        words.add(new Word("I will not leave.", "Ok abi wuok.",
                R.raw.ok_abi));
        words.add(new Word("I went.", "Ne adhi.",
                R.raw.ne_adhi));
        words.add(new Word("I had visited you.", "Ne alimow u.",
                R.raw.ne_alimow));
        words.add(new Word("I should wake up.", "Onego achiew.",
                R.raw.onego_achiew));
        words.add(new Word("I should have started.", "Ne onego achaki.",
                R.raw.ne_onego));
        words.add(new Word("I think, I was thinking, When I think", "Aparo, Aparoga, Ka aparo",
                R.raw.aparo_aparoga));
        words.add(new Word("Or/ So, Or/ Even/ Also/ Although/ Either/ Nor", "Koso, Kata",
                R.raw.koso_kata));
        words.add(new Word("The other ones.", "Mamoko",
                R.raw.mamoko));
        words.add(new Word("I am hungry.", "Kech kaya",
                R.raw.kech_kaya));
        words.add(new Word("The book for writing.", "Buk mar ndiko/ Buk ndiko.",
                R.raw.buk_mar));
        words.add(new Word("I must get it!", "Nyaka yudi!",
                R.raw.nyaka_yudi));
        words.add(new Word("These are..., Those are...", "Magi gin..., Maka gin...",
                R.raw.magi_maka));
        words.add(new Word("These are people from Nairobi.", "Magi gin jo Nairobi.",
                R.raw.magi_gin));
        words.add(new Word("I am from Asembo (If male), I am from Siaya (If female)", "An wuod Asembo/ An ja Asembo, An nyar Siaya,.",
                R.raw.an_wuod));
        words.add(new Word("We are from Uyoma (If females).", "Wan nyi Uyoma.",
                R.raw.wan_nyi));
        words.add(new Word("I did not go.", "Ne ok adhi.",
                R.raw.ne_ok));
        words.add(new Word("Do not go.", "Kik idhi.",
                R.raw.kik_idhi));
        words.add(new Word("They work for money.", "Gitiyo ni pesa.",
                R.raw.gitiyo_ni));
        words.add(new Word("He makes us laugh.", "Omiyo wanyiero.",
                R.raw.omiyo_wanyiero));
        words.add(new Word("I like it when you joke.", "Ahero ka kitimo ngera (oyuma).",
                R.raw.ahero_ka));
        words.add(new Word("I will invite you.", "Abiro gweli/ Abogweli.",
                R.raw.abiro_gweli));
        words.add(new Word("I like all of them equally.", "Ahero gi te maromore.",
                R.raw.ahero_gite));
        words.add(new Word("The builder is on top of the house.", "Jagedo nitie ewi ot.",
                R.raw.jagedo_nitie));
        words.add(new Word("Both sides", "Koni gi koni",
                R.raw.koni_gi));
        words.add(new Word("That (Near), That (Far)", "Mano, Macha",
                R.raw.mano_macha));
        words.add(new Word("That is a garden.", "Macha en puodho.",
                R.raw.macha_en));
        words.add(new Word("That chair, That home, That person...", "Komcha, Dalacha, Ng'atcha...",
                R.raw.komcha));
        words.add(new Word("That girl (Near), That girl (Far), Those girls", "Nyakono, Nyakocha, Nyirika",
                R.raw.nyakono_nyakocha));
        words.add(new Word("This girl, These girls", "Nyakoni, Nyirigi",
                R.raw.nyakoni_nyirigi));
        words.add(new Word("This area, That area", "Kae, Kacha",
                R.raw.kae_kacha));
        words.add(new Word("This area is for playing.", "Kae kar tugo",
                R.raw.kae_kar));
        words.add(new Word("Those (Near), Those (Far)", "Mago, Maka",
                R.raw.mago_maka));
        words.add(new Word("Those are chickens.", "Mago gwen.",
                R.raw.mago_guen));
        words.add(new Word("The house has been built from mud.", "Ot olos gi chuodho.",
                R.raw.ot_olos));
        words.add(new Word("The garden has been ploughed by dad.", "Puodho opur gi baba.",
                R.raw.puodho_opur));
        words.add(new Word("Otieno is the son of Odhiambo.", "Otieno en wuod Odhiambo.",
                R.raw.otieno_en));
        words.add(new Word("Question", "Penjo",
                R.raw.penjo));
        words.add(new Word("What?", "Ang'o?",
                R.raw.ango));
        words.add(new Word("What is this? What are these? What is that?", "Ma en ang'o? Ma ang'o? Ma gi tang'o? Macha ang'o?",
                R.raw.ma_enango));
        words.add(new Word("What is bothering you?", "Ang'o machandi?",
                R.raw.ango_machandi));
        words.add(new Word("What is your name? My name is Oluoch.", "Nyingi ng'a? Nyinga Oluoch.",
                R.raw.nyingi));
        words.add(new Word("How?", "Nadi?/ Nang'o?",
                R.raw.nadi_nango));
        words.add(new Word("How big?", "Oromo nadi?",
                R.raw.oromo_nadi));
        words.add(new Word("How many?", "Adi?",
                R.raw.adi));
        words.add(new Word("How many people are they?", "Gin ji adi?",
                R.raw.gin_ji));
        words.add(new Word("How much is it?", "Nengone en adi?",
                R.raw.nengone_en));
        words.add(new Word("When?", "Karang'o?",
                R.raw.karango));
        words.add(new Word("When will you come?", "Ibiro karang'o?",
                R.raw.ibiro_karango));
        words.add(new Word("Who? (Referring to more than one)", "Ng'ano?/ Ng'a?/ Ng'awa? (Ng'a gini)",
                R.raw.ngano_nga));
        words.add(new Word("Who is calling me?", "Ng'ano ma luonga?",
                R.raw.ngano_maluonga));
        words.add(new Word("Who is in the house?", "Ng'a ma nitie e ot?",
                R.raw.nga_ma));
        words.add(new Word("Why?", "Mar ang'o?",
                R.raw.mar_ango));
        words.add(new Word("Why do...?", "Ang'o momiyo...?",
                R.raw.ango_momiyo));
        words.add(new Word("Why do you get late?", "Ang'o momiyo ilewo?",
                R.raw.ango_ilweo));
        words.add(new Word("Where?", "Kanye?/ Kure?",
                R.raw.kanye_kure));
        words.add(new Word("Where do you come from? I am from Kisumu.", "To ia kanye? Aa Kisumu.",
                R.raw.to_ia));
        words.add(new Word("Where are you going?", "Idhi kure?",
                R.raw.idhi_kure));
        words.add(new Word("Where is the plate?, Where are the plates?", "Ere san?, Eke sende?",
                R.raw.ere_san));
        words.add(new Word("Which?/ Which one? (Referring to more than one)", "Mane? (Mage)",
                R.raw.mane_mage));
        words.add(new Word("Which animal do you like?", "Ihero le mane?",
                R.raw.ihero_le));
        words.add(new Word("Whose?", "Mar ng'a?",
                R.raw.mar_nga));
        words.add(new Word("Whose clothes did you wear?", "Irwako nanga mar ng'a?",
                R.raw.irwako_nango));
        words.add(new Word("Are you going? (Plural) Are we going? Is s/he going? Are they going?", "Be idhi? (Be udhi?) Be Wadhi? Be odhi? Be gidhi?",
                R.raw.be_idhi));
        words.add(new Word("Are we going to dance? Are you going to work? (Plural),  Are they drinking? ", "Wadhi miel? Idhii tich? (Udhii tich?), Gimetho?",
                R.raw.wadhi_miel));
        words.add(new Word("You are going, aren't you?", "Be idhi koso?",
                R.raw.be_koso));
        words.add(new Word("Aren't you going?", "Ok idhi koso?",
                R.raw.okidhi_koso));
        words.add(new Word("Have you heard about what has happened?", "Bende isewinjo gima otimore?",
                R.raw.bende_isewinjo));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_phrases);

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
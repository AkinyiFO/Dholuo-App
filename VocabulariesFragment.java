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
 * {@link Fragment} that displays a list of number vocabulary words.
 */
public class VocabulariesFragment extends Fragment {

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

    public VocabulariesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        // Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        // Create a list of words
        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("Numbers", "Kweno",
                R.raw.kweno));
        words.add(new Word("One", "Achiel",
                R.raw.achiel));
        words.add(new Word("Two", "Ariyo",
                R.raw.ariyo));
        words.add(new Word("Three", "Adek",
                R.raw.adek));
        words.add(new Word("Four", "Ang'wen",
                R.raw.angwen));
        words.add(new Word("Five", "Abich",
                R.raw.abich));
        words.add(new Word("Six", "Auchiel",
                R.raw.auchiel));
        words.add(new Word("Seven", "Abiriyo",
                R.raw.abiriyo));
        words.add(new Word("Eight", "Aboro",
                R.raw.aboro));
        words.add(new Word("Nine", "Ochiko",
                R.raw.ochiko));
        words.add(new Word("Ten", "Apar",
                R.raw.apar));
        words.add(new Word("Eleven, Twelve, Thirteen...", "Apar gachiel, Apar gariyo, Apar gadek...",
                R.raw.apar_gachiel));
        words.add(new Word("Twenty, Twenty one, Twenty two...", "Piero ariyo, Piero ariyo gachiel, Piero ariyo gariyo...",
                R.raw.piero_ariyo));
        words.add(new Word("Thirty, Forty, Fifty...", "Piero adek, Piero angwen, Piero abich...",
                R.raw.pier_adek));
        words.add(new Word("One hundred, Two hundred, Three hundred...", "Mia achiel, Mia ariyo, Mia adek...",
                R.raw.mia_achiel));
        words.add(new Word("One thousand, Two thousand, Three thousand...", "Alufu achiel, Alufu ariyo, Alufu adek...",
                R.raw.alufu_achiel));
        words.add(new Word("Counting", "Kweno",
                R.raw.kweno));
        words.add(new Word("First", "Mokuongo",
                R.raw.mokuongo));
        words.add(new Word("Second, Third...Tenth", "Mar ariyo, Mar adek...Mar apar",
                R.raw.mar_ariyo));
        words.add(new Word("Last", "Mogik",
                R.raw.mogik));
        words.add(new Word("Past/ Last", "Mokalo",
                R.raw.mokalo));
        words.add(new Word("Once", "Dichiel",
                R.raw.dichiel));
        words.add(new Word("Each one", "Moro ka moro",
                R.raw.moro_ka_moro));
        words.add(new Word("Other ones/ Another", "Moko, Moro",
                R.raw.moko_moro));
        words.add(new Word("Everyone/ Everybody", "Ng'ato ka ng'ato",
                R.raw.ngato_ka_ngato));
        words.add(new Word("Anyone", "Ng'ato a ng'ata",
                R.raw.ngato_a_ngata));
        words.add(new Word("Something, Somethings", "Gimoro, Gikmoko",
                R.raw.gimoro_gikmoko));
        words.add(new Word("Somewhere", "Kamoro",
                R.raw.kamoro));
        words.add(new Word("Sometimes", "Samoro",
                R.raw.samoro));
        words.add(new Word("Anything", "Gimoro a mora",
                R.raw.gimoro_a_mora));
        words.add(new Word("The beginning", "Chakruok",
                R.raw.chakruok));
        words.add(new Word("Human being/ Mankind, Human, People/ Crowd/ Nation", "Dhano, Ng'ato, Ji/ Oganda",
                R.raw.dhano));
        words.add(new Word("Girl, Girls, Woman, Women", "Nyako, Nyiri, Dhako, Mon",
                R.raw.nyako));
        words.add(new Word("Man/ Husband", "Dichuo",
                R.raw.dichuo));
        words.add(new Word("Body, Bodies", "Del, Dend",
                R.raw.del_dend));
        words.add(new Word("Human body", "Dend dhano",
                R.raw.dend_dhano));
        words.add(new Word("Head, Heads", "Wi/ Wich, Wiye",
                R.raw.wi_wich_wiye));
        words.add(new Word("Forehead", "Pat wang'/ Pat wich",
                R.raw.pat_wang));
        words.add(new Word("Eye/ Face/ Later today/ Soon/ Now, Eyes/ Faces", "Wang', Wenge",
                R.raw.wang_wenge));
        words.add(new Word("Nose", "Um",
                R.raw.um));
        words.add(new Word("Ear", "It",
                R.raw.it));
        words.add(new Word("Cheek", "Lemb",
                R.raw.lemb));
        words.add(new Word("Jaw", "Chok lemb/ Chok lak",
                R.raw.chok_lemb));
        words.add(new Word("Mouth", "Dhok",
                R.raw.dhok));
        words.add(new Word("Tooth, Teeth", "Lak, Leke",
                R.raw.lak_leke));
        words.add(new Word("Tongue", "Lep, Lew",
                R.raw.lep_lew));
        words.add(new Word("Hair", "Yier wich/ Yier",
                R.raw.yier_wich));
        words.add(new Word("Neck", "Ng'ut",
                R.raw.ngut));
        words.add(new Word("Throat", "Duol",
                R.raw.duol));
        words.add(new Word("Shoulder", "Gok",
                R.raw.gok));
        words.add(new Word("Arm, Arms", "Bat, Bede",
                R.raw.bat_bede));
        words.add(new Word("Elbow", "Okumbo",
                R.raw.okumbo));
        words.add(new Word("Hand, Hands", "Lwedo/ Luedo, Lwete/ Luete",
                R.raw.lwedo_lwete));
        words.add(new Word("Finger", "Lith lwedo",
                R.raw.lith_lwedo));
        words.add(new Word("Nail", "Kogno",
                R.raw.kogno));
        words.add(new Word("Chest", "Kor",
                R.raw.kor));
        words.add(new Word("Breast, Breasts", "Thuno, Thunde",
                R.raw.thuno_thunde));
        words.add(new Word("Lung", "Obo",
                R.raw.obo));
        words.add(new Word("Rib", "Ng'et",
                R.raw.nget));
        words.add(new Word("Back", "Tok",
                R.raw.tok));
        words.add(new Word("Heart, Hearts", "Adundo, Adundni",
                R.raw.adundo));
        words.add(new Word("Blood", "Remo/ Remb",
                R.raw.remo_remb));
        words.add(new Word("Liver", "Chuny",
                R.raw.chuny));
        words.add(new Word("Stomach/ Womb, Stomachs", "Ich, Iye",
                R.raw.ich_iye));
        words.add(new Word("Intestines", "Nyimbich/ Gik ich",
                R.raw.nyimbich));
        words.add(new Word("Kidney, Kidneys", "Rogno, Nyiroke",
                R.raw.rogno));
        words.add(new Word("Navel", "Pend",
                R.raw.pend));
        words.add(new Word("Buttocks", "Pier, Sianda",
                R.raw.pier_sianda));
        words.add(new Word("Leg, Legs", "Tielo/ Ogwalo, Tiende",
                R.raw.tielo_ogwalo));
        words.add(new Word("Thigh", "Em",
                R.raw.em));
        words.add(new Word("Knee, Knees", "Chong, Chonge",
                R.raw.chong_chonge));
        words.add(new Word("Skin, Skins", "Pien, Piende",
                R.raw.pien_piende));
        words.add(new Word("Hunch back", "Rakuom",
                R.raw.rakuom));
        words.add(new Word("Person living with disability", "Rang'ol",
                R.raw.rangol));
        words.add(new Word("Nouns/ Names", "Nyinge",
                R.raw.nyinge));
        words.add(new Word("Work", "Tich",
                R.raw.tich));
        words.add(new Word("Salary/ Bill", "Chudo",
                R.raw.chudo));
        words.add(new Word("Date", "Tarik",
                R.raw.tarik));
        words.add(new Word("Day, Days", "Odiechieng', Ndalo",
                R.raw.odiechieng_ndalo));
        words.add(new Word("Week, Weeks", "Juma",
                R.raw.juma));
        words.add(new Word("Monday", "Wuok tich",
                R.raw.wuok_tich));
        words.add(new Word("Tuesday", "Tich ariyo",
                R.raw.tich_ariyo));
        words.add(new Word("Wednesday", "Tich adek",
                R.raw.tich_adek));
        words.add(new Word("Thursday", "Tich ang'wen",
                R.raw.tich_angwen));
        words.add(new Word("Friday", "Tich abich",
                R.raw.tich_abich));
        words.add(new Word("Saturday", "Ngeso/ Sabato/ Chieng' Ngeso/ Chieng' Sabato",
                R.raw.ngeso));
        words.add(new Word("Sunday", "Chieng' Nyasaye/ Chieng' Odira/ Chieng' Juma",
                R.raw.chieng_nyasae));
        words.add(new Word("Holiday", "Kinde yueyo/ Diro/ Odira",
                R.raw.kinde_yueyo));
        words.add(new Word("Year, Years", "Iga, Igni",
                R.raw.iga_igni));
        words.add(new Word("Month, Months", "Dwe, Dweche",
                R.raw.dwe));
        words.add(new Word("January, February...December.", "Dwe mar achiel, Dwe mar ariyo, Dwe mar apar gi ariyo/ Dwe mar apar gariyo.",
                R.raw.due_mar_achiel));
        words.add(new Word("Time", "Sache",
                R.raw.sache));
        words.add(new Word("A few days ago", "Yande",
                R.raw.yande));
        words.add(new Word("Now", "Sani",
                R.raw.sani));
        words.add(new Word("Now/ Then/ So", "Koro",
                R.raw.koro));
        words.add(new Word("Later", "Bang'e",
                R.raw.bange));
        words.add(new Word("After", "Bang'",
                R.raw.bang));
        words.add(new Word("Nowadays", "Kindegi, Ndalogi, Tinde/ Odiechieng'egi",
                R.raw.kindegi));
        words.add(new Word("New/ Recent", "Nyien",
                R.raw.nyien));
        words.add(new Word("Old", "Oti/ Onyoch",
                R.raw.oti));
        words.add(new Word("Hour, Hours", "Kinde/ Sa, Seche",
                R.raw.kinde_sa));
        words.add(new Word("Minute, Minutes", "Dakika",
                R.raw.dakika));
        words.add(new Word("Morning", "Okinyi",
                R.raw.okinyi));
        words.add(new Word("Noon", "Odiechieng' tir",
                R.raw.odiechieng_tir));
        words.add(new Word("Evening", "Odhiambo",
                R.raw.odhiambo));
        words.add(new Word("Night", "Otieno",
                R.raw.otieno));
        words.add(new Word("Everyday", "Odiechieng' ka odiechieng'/ Pile ka pile/ Pile pile",
                R.raw.odiechieng_ka_odiechieng));
        words.add(new Word("The day before yesterday", "Nyocha",
                R.raw.nyocha));
        words.add(new Word("Yesterday", "Nyoro",
                R.raw.nyoro));
        words.add(new Word("Today", "Gonyo/ Kawuono",
                R.raw.gonyo));
        words.add(new Word("Tomorrow", "Kiny",
                R.raw.kiny));
        words.add(new Word("The day after tomorrow", "Orucha",
                R.raw.orucha));
        words.add(new Word("Paradise/ Sky", "Polo",
                R.raw.polo));
        words.add(new Word("Sun", "Chieng'",
                R.raw.chieng));
        words.add(new Word("Bright/ Brilliance", "Marieny",
                R.raw.marieny));
        words.add(new Word("Light", "Ler",
                R.raw.ler));
        words.add(new Word("Moon", "Due",
                R.raw.due));
        words.add(new Word("Darkness", "Mudho",
                R.raw.mudho));
        words.add(new Word("Star", "Sulwe",
                R.raw.sulwe));
        words.add(new Word("Cloud", "Boche polo",
                R.raw.boche_polo));
        words.add(new Word("Lightning", "Mir mar polo",
                R.raw.mir_mar_polo));
        words.add(new Word("Atmosphere", "Lwasi",
                R.raw.lwasi));
        words.add(new Word("Air", "Muya",
                R.raw.muya));
        words.add(new Word("Rain", "Koth",
                R.raw.koth));
        words.add(new Word("Wind", "Yamo",
                R.raw.yamo));
        words.add(new Word("Fog, Rainy season, Dry season/ Summer, Cold", "Ong'weng'o, Chwiri, Oro, Koyo",
                R.raw.ongwengo));
        words.add(new Word("Chapter", "Sula",
                R.raw.sula));
        words.add(new Word("Cold", "Ng'ich",
                R.raw.ngich));
        words.add(new Word("Warm", "Mormor",
                R.raw.mormor));
        words.add(new Word("Hot (adjective, weather)", "(Liet, Liet)",
                R.raw.liet));
        words.add(new Word("Sweat", "Luya/ Kuok",
                R.raw.luya));
        words.add(new Word("Wet", "Mang'ich/ Modhiek",
                R.raw.mangich));
        words.add(new Word("Cool", "Maokue",
                R.raw.maokue));
        words.add(new Word("Soft/ Easy", "Yom",
                R.raw.yom));
        words.add(new Word("Hard", "Tek",
                R.raw.tek));
        words.add(new Word("Sweet/ Tasty", "Mit",
                R.raw.mit));
        words.add(new Word("Bitter", "Makech",
                R.raw.makech));
        words.add(new Word("Sour", "Mawachwach",
                R.raw.mawachwach));
        words.add(new Word("Uncooked/ Unripe", "Manumu",
                R.raw.manumu));
        words.add(new Word("Ripe/ Ready", "Chiek",
                R.raw.chiek));
        words.add(new Word("Clean/ Holy", "Maler",
                R.raw.maler));
        words.add(new Word("Dirty", "Molil",
                R.raw.molil));
        words.add(new Word("Brown female", "Malando",
                R.raw.malando));
        words.add(new Word("Black female", "Madichol",
                R.raw.madichol));
        words.add(new Word("Brown man", "Masilwal",
                R.raw.masilwal));
        words.add(new Word("Great/ Huge/ Big (Referring to more than one)", "Mang'ongo/ Maduong' (Madongo)",
                R.raw.mangongo_maduong));
        words.add(new Word("Few/ Small/ Little/ Young", "Matin, Matindo",
                R.raw.matin_matindo));
        words.add(new Word("A lot/ Many/ Much", "Mang'eny",
                R.raw.mangeny));
        words.add(new Word("All", "Duto/ Te",
                R.raw.duto_te));
        words.add(new Word("Thin", "Odhero",
                R.raw.odhero));
        words.add(new Word("Narrow", "Madiny",
                R.raw.madiny));
        words.add(new Word("Wide/ Large", "Malach",
                R.raw.malach));
        words.add(new Word("Enough/ Worth it/ Satisfied", "Romo",
                R.raw.romo));
        words.add(new Word("Meet/ Encounter/ Meeting", "Romo",
                R.raw.romo_meet));
        words.add(new Word("Full, Satisfied", "Yieng'",
                R.raw.yieng));
        words.add(new Word("Abundant", "Mathoth",
                R.raw.mathoth));
        words.add(new Word("Plenty", "Maogundho",
                R.raw.maogundho));
        words.add(new Word("Short (Referring to more than one)", "Machiek (Machieko)",
                R.raw.machiek_machieko));
        words.add(new Word("Tall, Long (Referring to more than one)", "Bor, Bor (Boyo)",
                R.raw.bor_boyo));
        words.add(new Word("Round", "Oluorore",
                R.raw.oluorore));
        words.add(new Word("Fat", "Chwe",
                R.raw.chwe));
        words.add(new Word("Light weight/ Cheap/ Easy", "Mayot",
                R.raw.mayot));
        words.add(new Word("Heavy", "Mapek",
                R.raw.mapek));
        words.add(new Word("Slim woman", "Marapudo",
                R.raw.marapudo));
        words.add(new Word("Slim man", "Marandere",
                R.raw.marandere));
        words.add(new Word("Wise", "Rieko",
                R.raw.rieko));
        words.add(new Word("Strong", "Tegno",
                R.raw.tegno));
        words.add(new Word("Weak", "Yomyom/ Nyap",
                R.raw.yomyom));
        words.add(new Word("Tired", "Mool",
                R.raw.mool));
        words.add(new Word("Inferior", "Machien",
                R.raw.machien));
        words.add(new Word("Jealous", "Maranyiego",
                R.raw.maranyiego));
        words.add(new Word("Sad", "Sin",
                R.raw.sin));
        words.add(new Word("Happy/ Joy", "Mor/ Yilo",
                R.raw.mor));
        words.add(new Word("Joke", "Ngera/ Oyuma",
                R.raw.ngera));
        words.add(new Word("Bad (Referring to more than one)", "Rach (Maricho)",
                R.raw.rach_maricho));
        words.add(new Word("Ugly", "Mararach",
                R.raw.mararach));
        words.add(new Word("Beauty/ Good/ Well (Referring to more than one)", "Ber (Beyo)",
                R.raw.ber_beyo));
        words.add(new Word("Nice/ Beautiful/ Wonderful/ Dangerous/ Unheard of/ Strange", "Malich",
                R.raw.malich));
        words.add(new Word("Pride", "Nyadhi",
                R.raw.nyadhi));
        words.add(new Word("Shame/ Shy", "Wichkuot",
                R.raw.wichkuot));
        words.add(new Word("Greed", "Wuoro",
                R.raw.wuoro));
        words.add(new Word("Pace", "Kue",
                R.raw.kue));
        words.add(new Word("Slow", "Mos",
                R.raw.mos));
        words.add(new Word("Fast/ Quick", "Piyo",
                R.raw.piyo));
        words.add(new Word("Humble", "Maodimbore/ Mamos",
                R.raw.maodimbore_mamos));
        words.add(new Word("Mean", "Mangudi",
                R.raw.mangudi));
        words.add(new Word("Right", "Kare/ Odimbore",
                R.raw.kare));
        words.add(new Word("Clever/ Brilliant", "Riek",
                R.raw.riek));
        words.add(new Word("Foolish", "Ofuwo",
                R.raw.ofuwo));
        words.add(new Word("Honest", "Maodimbore/ Makare",
                R.raw.maodimbore_makare));
        words.add(new Word("Truth", "Adiera",
                R.raw.adiera));
        words.add(new Word("Lie", "Riambo",
                R.raw.riambo));
        words.add(new Word("Hope/ Trust", "Geno",
                R.raw.geno));
        words.add(new Word("Direction", "Ranyisi mag yore",
                R.raw.ranyisi_mag_yore));
        words.add(new Word("Down/ On the ground/ Low/ Earth/ World/ Country, Countries", "Piny, Pinje",
                R.raw.piny_pinje));
        words.add(new Word("Low", "Mwalo",
                R.raw.mwalo));
        words.add(new Word("Up/ High", "Malo",
                R.raw.malo));
        words.add(new Word("Under", "E bwo/ E buo/ Etie",
                R.raw.ebwo_etie));
        words.add(new Word("Inside", "Ei",
                R.raw.ei));
        words.add(new Word("Outside/ Excrement", "Oko",
                R.raw.oko));
        words.add(new Word("Behind/ Back", "Chien",
                R.raw.chien));
        words.add(new Word("Behind", "Toke",
                R.raw.toke));
        words.add(new Word("Near/ Close", "Machiegni",
                R.raw.machiegni));
        words.add(new Word("Far", "Yier",
                R.raw.yier));
        words.add(new Word("East, Eastwards", "Ugwe, Yor ugwe",
                R.raw.ugwe_yor_ugwe));
        words.add(new Word("West, Westwards", "Yimbo, Yor yimbo",
                R.raw.yimbo_yor_yimbo));
        words.add(new Word("South, Southwards", "Milambo, Yor milambo",
                R.raw.milambo_yor_milambo));
        words.add(new Word("North, Northwards", "Masawa/ Nyandwat, Yor masawa/ Yor nyandwat",
                R.raw.masawa_nyandwat));
        words.add(new Word("Family, Relation", "Joot, Wat",
                R.raw.joot_wat));
        words.add(new Word("Relative, Relatives", "Wat, Wede",
                R.raw.wat_wede));
        words.add(new Word("Father, Our father, Elder, Elders", "Wuoro/ Wuon, Wuonwa, Jaduong', Jodongo",
                R.raw.wuoro_wuon));
        words.add(new Word("Marriage", "Kendruok/ Nyombo/ Kend/ Keny",
                R.raw.kendruok_nyombo));
        words.add(new Word("Husband/ Wife/ Spouse, Spouses", "Jaot, Joudi",
                R.raw.jaot_joudi));
        words.add(new Word("Wife, Wives", "Chieg, Chi",
                R.raw.chieg_chi));
        words.add(new Word("First wife", "Mikayo/ Mikach",
                R.raw.mikayo_mikach));
        words.add(new Word("Second wife", "Nyachira",
                R.raw.nyachira));
        words.add(new Word("Third wife", "Reru",
                R.raw.reru));
        words.add(new Word("Co-wife, My Co-wife", "Nyiek, Nyieka",
                R.raw.nyiek_nyieka));
        words.add(new Word("Bachelor/ Widower", "Misumba",
                R.raw.misumba));
        words.add(new Word("Widow", "Chi liel",
                R.raw.chi_liel));
        words.add(new Word("One who marries a widow", "Jater",
                R.raw.jater));
        words.add(new Word("Mother, Our mother", "Min/ Miyo, Minwa",
                R.raw.min_miyo));
        words.add(new Word("Parent", "Janyuol",
                R.raw.janyuol));
        words.add(new Word("Child, Children, My child, My children", "Nyathi, Nyithindo, Nyathina, Nyithinda",
                R.raw.nyathi_nyithindo));
        words.add(new Word("First born", "Kayo/ Kach",
                R.raw.kayo_kach));
        words.add(new Word("Last born", "Chogo",
                R.raw.chogo));
        words.add(new Word("Himself/ Herself", "Kende",
                R.raw.kende));
        words.add(new Word("Sister, Sisters, Brother, Brothers, My brother", "Nyamin, Nyimine, Owad, Owete, Owadwa",
                R.raw.nyamin_nyimine));
        words.add(new Word("Brother-in-law", "Yuore",
                R.raw.yuore));
        words.add(new Word("Son, My son", "Wuod, Wuoda",
                R.raw.wuod));
        words.add(new Word("Boy, Man, Men", "Wuoyi, Dichuo, Chuo",
                R.raw.wuoyi));
        words.add(new Word("Daughter, My daughter", "Nyar, Nyara",
                R.raw.nyar_nyara));
        words.add(new Word("Youth", "Yawere",
                R.raw.yawere));
        words.add(new Word("Same age group", "Mbas, Mbese",
                R.raw.mbas_mbese));
        words.add(new Word("Uncle, My uncle", "Ner, Nera",
                R.raw.ner_nera));
        words.add(new Word("Aunt, My aunt", "Way, Waya",
                R.raw.way_waya));
        words.add(new Word("Grandfather, My grandfather", "Kwaro, Kwaru",
                R.raw.kwaro_kwaru));
        words.add(new Word("Stick, Sticks", "Luth, Ludhe",
                R.raw.luth_ludhe));
        words.add(new Word("Walking stick", "Luth",
                R.raw.luth));
        words.add(new Word("Old woman, Old women, Grandmother", "Dayo, Deye, Dani/ Dana",
                R.raw.dayo_deye));
        words.add(new Word("Grandchild, Grandchildren", "Nyakwaro, Nyikweye",
                R.raw.nyakwaro_nyikweye));
        words.add(new Word("Orphan", "Kich",
                R.raw.kich));
        words.add(new Word("My neighbour, My neighbours", "Jabatha, Jobatha",
                R.raw.jabatha_jobatha));
        words.add(new Word("Close male friend", "Omera",
                R.raw.omera));
        words.add(new Word("Friend, Friends", "Osiep, Osiepe",
                R.raw.osiep_osiepe));
        words.add(new Word("Guest, Guests", "Wendo, Welo",
                R.raw.wendo_welo));
        words.add(new Word("Example/ Picture/ Sign/ Symbol", "Ranyisi",
                R.raw.ranyisi));
        words.add(new Word("Red", "Rakwar",
                R.raw.rakwar));
        words.add(new Word("Green", "Majan",
                R.raw.majan));
        words.add(new Word("Blue", "Rambulu",
                R.raw.rambulu));
        words.add(new Word("Yellow", "Ratong' gweno",
                R.raw.ratong_gweno));
        words.add(new Word("Brown", "Rabuor",
                R.raw.rabuor));
        words.add(new Word("Black (Referring to more than one)", "Rateng' (Ratenge)",
                R.raw.rateng_ratenge));
        words.add(new Word("White (Referring to more than one)", "Rachar (Rochere)",
                R.raw.rachar_ruchere));
        words.add(new Word("Black and white", "Radier",
                R.raw.radier));
        words.add(new Word("Grey", "Ralik",
                R.raw.ralik));
        words.add(new Word("Purple", "Ratiglo",
                R.raw.ratiglo));
        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_numbers);

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
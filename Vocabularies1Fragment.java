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
 * {@link Fragment} that displays a list of color vocabulary words.
 */
public class Vocabularies1Fragment extends Fragment {

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

    public Vocabularies1Fragment() {
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
        words.add(new Word("Next", "Machielo/ Mabiro",
                R.raw.machielo_mabiro));
        words.add(new Word("Place", "Kamoro",
                R.raw.kamoro));
        words.add(new Word("Space/ Period of time/ Occasion", "Kinde",
                R.raw.kinde));
        words.add(new Word("Place of, Places of", "Kar, Kuonde",
                R.raw.kar_kuonde));
        words.add(new Word("Village, Villages", "Gweng'/ Mier, Gwenge/ Mieche",
                R.raw.gweng));
        words.add(new Word("Home/ Homestead, Homes, Homesteads", "Pacho/ Dala, Mier",
                R.raw.pacho_dala));
        words.add(new Word("House, Houses, Hut", "Ot, Udi, Abila/ Duol/ Kondo",
                R.raw.ot_udi));
        words.add(new Word("Veranda", "Agola",
                R.raw.agola));
        words.add(new Word("Door, Gate", "Dhoot, Rangach",
                R.raw.dhoot_rangach));
        words.add(new Word("Mat", "Par",
                R.raw.par));
        words.add(new Word("Key", "Rayaw",
                R.raw.rayawo));
        words.add(new Word("Lock", "Rarind",
                R.raw.rarind));
        words.add(new Word("Window", "Otuchi",
                R.raw.otuchi));
        words.add(new Word("Lamb, Lambs", "Taya, Teyni/ Tach",
                R.raw.taya));
        words.add(new Word("Floor", "Dier ot",
                R.raw.dier_ot));
        words.add(new Word("Broom", "Ywech",
                R.raw.ywech));
        words.add(new Word("Sweep", "Ywecho",
                R.raw.ywecho));
        words.add(new Word("Bundle of grass/ firewood", "Wich",
                R.raw.wich));
        words.add(new Word("Roof", "Tado",
                R.raw.tado));
        words.add(new Word("Sitting room", "Kar budho",
                R.raw.kar_budho));
        words.add(new Word("Chair, Chairs, Couch", "Kom, Kombe, Komb sofa",
                R.raw.kom_kombe));
        words.add(new Word("Table", "Mesa",
                R.raw.mesa));
        words.add(new Word("Sleeping place", "Kar ka chiena",
                R.raw.kar_ka_chiena));
        words.add(new Word("Bed", "Uriri",
                R.raw.uriri));
        words.add(new Word("Mattress", "Godhro",
                R.raw.godhro));
        words.add(new Word("Blanket", "Onget/ Baranget",
                R.raw.onget_baranget));
        words.add(new Word("Sheet, Sheets", "Suka, Sukni/ Suke",
                R.raw.suka_sukni));
        words.add(new Word("Garment/ Cloth, Clothes", "Lew/ Nanga, Leuni",
                R.raw.lew_nanga));
        words.add(new Word("Wear/ Welcome/ Accept", "Ruako",
                R.raw.ruako));
        words.add(new Word("Naked", "Maduk",
                R.raw.maduk));
        words.add(new Word("Shoe, Shoes", "Wuor, Wuoche",
                R.raw.wuor_wuoche));
        words.add(new Word("Sandal", "Ngato/ Champat/ Akala",
                R.raw.ngato_champat));
        words.add(new Word("Slippers", "Pat kira",
                R.raw.pat_kira));
        words.add(new Word("Stove, Again, Marry", "Kendo, Kendo, Kendo",
                R.raw.kendo));
        words.add(new Word("Marry each other", "Kendre",
                R.raw.kendre));
        words.add(new Word("Fire, Fires", "Mach, Meye",
                R.raw.mach_meye));
        words.add(new Word("Dish/ Plate, Dishes, Plates", "San, Sende",
                R.raw.san_sende));
        words.add(new Word("Cup", "Okombe",
                R.raw.okombe));
        words.add(new Word("Handle", "Ramak",
                R.raw.ramak));
        words.add(new Word("Knife, Knives", "Pala, Pelni/ Pende",
                R.raw.pala_pelni));
        words.add(new Word("Calabash", "Agwata",
                R.raw.agwata));
        words.add(new Word("Gourd", "Apiga/ Puga",
                R.raw.apiga));
        words.add(new Word("Clay bowl", "Tawo",
                R.raw.tawo));
        words.add(new Word("Pot", "Agulu",
                R.raw.agulu));
        words.add(new Word("Lid", "Raum",
                R.raw.raum));
        words.add(new Word("Pail", "Ndowo",
                R.raw.ndowo));
        words.add(new Word("Wood/ Tree", "Yien",
                R.raw.yien));
        words.add(new Word("Smoke", "Yiro",
                R.raw.yiro));
        words.add(new Word("Modern", "Nyien, Ndalogi",
                R.raw.ndalogi));
        words.add(new Word("Mask", "Ong'eng'ore",
                R.raw.ongengore));
        words.add(new Word("Owner", "Wuon",
                R.raw.wuon));
        words.add(new Word("Car, Cars, Bicycle/ Wheel, Bicycles, Train, Trains", "Mitoka, Mitokni, Ndiga, Ndigni, Gari, Geche",
                R.raw.mitoka));
        words.add(new Word("Power", "Teko",
                R.raw.teko));
        words.add(new Word("Wealth", "Muandu",
                R.raw.muandu));
        words.add(new Word("Health/ Life", "Ngima",
                R.raw.ngima));
        words.add(new Word("Sickness", "Tuo",
                R.raw.tuo));
        words.add(new Word("Vomit", "Ng'ok",
                R.raw.ngok));
        words.add(new Word("Pain/ Sorrow", "Lit",
                R.raw.lit));
        words.add(new Word("Swell", "Kuot/ Kuodo",
                R.raw.kuot_kuodo));
        words.add(new Word("Wound", "Adhola",
                R.raw.adhola));
        words.add(new Word("Pus", "Tutu",
                R.raw.tutu));
        words.add(new Word("Scar", "Mbala",
                R.raw.mbala));
        words.add(new Word("Cigarette, Alcohol, Drinking straw", "Ndawa, Kong'o, Oseke",
                R.raw.ndawa));
        words.add(new Word("Gift", "Mich/ Chiwo",
                R.raw.mich));
        words.add(new Word("Price", "Nengo",
                R.raw.nengo));
        words.add(new Word("Gun", "Bunde",
                R.raw.bunde));
        words.add(new Word("Drum, Drums", "Bul, Bunde",
                R.raw.bul));
        words.add(new Word("Whip", "Del",
                R.raw.del));
        words.add(new Word("Thorn", "Kudho",
                R.raw.kudho));
        words.add(new Word("Horn", "Tung'",
                R.raw.tung));
        words.add(new Word("Axe", "Le",
                R.raw.le));
        words.add(new Word("Hoe", "Kwer/ Kwe/ Rapur",
                R.raw.kwer));
        words.add(new Word("Machete/ Panga", "Ratong'/ Beti",
                R.raw.ratong));
        words.add(new Word("Torch", "Rameny",
                R.raw.rameny));
        words.add(new Word("Mirror", "Rang'i",
                R.raw.rangi_mirror));
        words.add(new Word("Image", "Chalo",
                R.raw.chalo));
        words.add(new Word("Music/ Guitar", "Thum",
                R.raw.thum));
        words.add(new Word("Song/ Sing, Songs", "Wer, Wende",
                R.raw.wer));
        words.add(new Word("Hat", "Ogudu",
                R.raw.ogudu));
        words.add(new Word("Pen, Pens", "Kalam, Kalembe",
                R.raw.kalam));
        words.add(new Word("Comb", "Ragol",
                R.raw.ragol));
        words.add(new Word("Rope/ String (Plural)", "Tol/ Uno (Tonde)",
                R.raw.tol));
        words.add(new Word("Yoke", "Jok",
                R.raw.jok));
        words.add(new Word("Granary", "Dero",
                R.raw.dero));
        words.add(new Word("Food, Foods", "Chiemo, Chiembe",
                R.raw.chiemo));
        words.add(new Word("Water", "Pi",
                R.raw.pi));
        words.add(new Word("Ice", "Pe",
                R.raw.pe));
        words.add(new Word("Milk", "Chak",
                R.raw.chak));
        words.add(new Word("Soup", "Kado",
                R.raw.kado));
        words.add(new Word("Salt", "Ratuon",
                R.raw.ratuon));
        words.add(new Word("Butter", "Siage",
                R.raw.siage));
        words.add(new Word("Porridge", "Nyuka",
                R.raw.nyuka));
        words.add(new Word("Vegetables", "Alot",
                R.raw.alot));
        words.add(new Word("Maize", "Oduma/ Bando",
                R.raw.oduma));
        words.add(new Word("Flour", "Mogo",
                R.raw.mogo));
        words.add(new Word("Beans", "Oganda",
                R.raw.oganda));
        words.add(new Word("Seed", "Kodhi",
                R.raw.kodhi));
        words.add(new Word("Cereal/ Grains", "Cham",
                R.raw.cham));
        words.add(new Word("Finger millet", "Kal",
                R.raw.kal));
        words.add(new Word("Ugali", "Kuon",
                R.raw.kuon));
        words.add(new Word("Potato, Potatoes", "Rabuon, Rabuonde",
                R.raw.rabuon));
        words.add(new Word("Egg", "Tong'",
                R.raw.tong));
        words.add(new Word("Meat", "Ring'o",
                R.raw.ringo));
        words.add(new Word("Bone, Bones", "Chogo, Choke",
                R.raw.chogo_bone));
        words.add(new Word("Fruit, Fruits", "Olemo, Olembe",
                R.raw.olemo));
        words.add(new Word("Banana, Bananas", "Rabolo, Rabonde",
                R.raw.rabolo));
        words.add(new Word("Coconut", "Nas",
                R.raw.nas));
        words.add(new Word("Pawpaw", "Poi",
                R.raw.poi));
        words.add(new Word("Fish, Fishing net, Fisherman, Fishermen", "Rech, Gogo, Jalupo, Jolupo",
                R.raw.rech));
        words.add(new Word("Tilapia", "Ngege",
                R.raw.ngege));
        words.add(new Word("Trout", "Ningu",
                R.raw.ningu));
        words.add(new Word("Nile perch", "Mbuta",
                R.raw.mbuta));
        words.add(new Word("Mud fish", "Kamongo",
                R.raw.kamongo));
        words.add(new Word("Cat fish", "Mumi",
                R.raw.mumi));
        words.add(new Word("Animals, Domestic animals", "Le, Jamni",
                R.raw.le_jamni));
        words.add(new Word("Animals' pen", "Kund",
                R.raw.kund));
        words.add(new Word("Kitten, Cat, Cats", "Nyambura, Mbura, Mbuche",
                R.raw.nyambura));
        words.add(new Word("Puppy, Puppies, Dog, Dogs", "Nyaguok, Nyiguok, Guok, Guogi",
                R.raw.nyaguok));
        words.add(new Word("Goat, Goats, He-goat, She-goat", "Diel, Diek, Nyuok, Swini",
                R.raw.diel));
        words.add(new Word("Sheep, Sheeps", "Rombo, Rombe",
                R.raw.rombo));
        words.add(new Word("Bull/ Ox, Male calf, Female Cow", "Ruath/ Rwath, Nyarwath, Duasi",
                R.raw.ruath));
        words.add(new Word("Cow, Cows, Calf, Calves", "Dhiang', Dhok, Nyaroya/Roya, Nyiroye/Roye",
                R.raw.dhiang));
        words.add(new Word("Pig", "Anguro",
                R.raw.anguro));
        words.add(new Word("Rabbit, Rabbits", "Apuoyo, Apuoche",
                R.raw.apuoyo));
        words.add(new Word("Rat", "Oyieyo",
                R.raw.oyieyo));
        words.add(new Word("Squirrel", "Ayidha",
                R.raw.ayidha));
        words.add(new Word("Snake, Snakes", "Thuol, Thuonde",
                R.raw.thuol));
        words.add(new Word("Chameleon", "Ng'ong ruok",
                R.raw.ngong_ruok));
        words.add(new Word("Frog/ Toad", "Ogwal",
                R.raw.ogwal));
        words.add(new Word("Lizard", "Ogwe",
                R.raw.ogwe));
        words.add(new Word("Hyena, Hyenas", "Ondiek/ Otoyo, Otoche",
                R.raw.ondiek));
        words.add(new Word("Leopard", "Kwach",
                R.raw.kwach));
        words.add(new Word("Lion, Lioness", "Sibuor, Sibuor madhako",
                R.raw.sibuor));
        words.add(new Word("Rhinoceros", "Omuga",
                R.raw.omuga));
        words.add(new Word("Giraffe", "Tiga",
                R.raw.tiga));
        words.add(new Word("Buffalo", "Jowi",
                R.raw.jowi));
        words.add(new Word("Elephant", "Liech",
                R.raw.liech));
        words.add(new Word("Hippopotamus", "Rawo",
                R.raw.rawo));
        words.add(new Word("Crocodile", "Nyang'",
                R.raw.nyang));
        words.add(new Word("Wild cat", "Ogwang'",
                R.raw.ogwang));
        words.add(new Word("Monkey, Monkeys", "Ong'er, Ong'eche",
                R.raw.onger));
        words.add(new Word("Ape, Apes", "Bim, Bimbe",
                R.raw.bim));
        words.add(new Word("Warthog", "Mbidhi",
                R.raw.mbidhi));
        words.add(new Word("Tortoise", "Opuk",
                R.raw.opuk));
        words.add(new Word("Bird", "Winyo",
                R.raw.winyo));
        words.add(new Word("Chicken, Chickens", "Gueno, Guende",
                R.raw.gueno));
        words.add(new Word("Cock, Hen", "Thuon, Nyabur",
                R.raw.thuon));
        words.add(new Word("Turkey", "Mbata",
                R.raw.mbata));
        words.add(new Word("Owl", "Tula",
                R.raw.tula));
        words.add(new Word("Dove, Doves", "Akuru, Akuche",
                R.raw.akuru));
        words.add(new Word("Quail, Quails", "Aluru, Aluche",
                R.raw.aluru));
        words.add(new Word("Swallow", "Opija",
                R.raw.opija));
        words.add(new Word("Weaver bird", "Osogo",
                R.raw.osogo));
        words.add(new Word("Sunbird", "Onyodho",
                R.raw.onyodho));
        words.add(new Word("Crane", "Owang",
                R.raw.owang));
        words.add(new Word("Hawk", "Otenga",
                R.raw.otenga));
        words.add(new Word("Vulture", "Achuth",
                R.raw.achuth));
        words.add(new Word("Ostrich", "Udo",
                R.raw.udo));
        words.add(new Word("Insect, Insects", "Kud, Kudni",
                R.raw.kud));
        words.add(new Word("Bee/ Bees, Wasp, Wasps", "Kich, Pino, Pinde",
                R.raw.kich_bees));
        words.add(new Word("Locust, Grasshopper", "Ongogo, Dede",
                R.raw.ongogo));
        words.add(new Word("Cockroach", "Olwenda",
                R.raw.olwenda));
        words.add(new Word("Mosquito", "Suna",
                R.raw.suna));
        words.add(new Word("Spider", "Otieng'",
                R.raw.otieng));
        words.add(new Word("Termites", "Biye",
                R.raw.biye));
        words.add(new Word("Ants", "Ochunglo",
                R.raw.ochunglo));
        words.add(new Word("Snail", "Kamnie",
                R.raw.kamnie));
        words.add(new Word("Worm", "Kudni/ Njofni",
                R.raw.kudni));
        words.add(new Word("Butterfly, Butterflies", "Oguyo, Oguche",
                R.raw.oguyo));
        words.add(new Word("Path, Road, Roads, Way, Ways", "Apaya, Ndara, Ndeche, Yoo, Yore",
                R.raw.apaya));
        words.add(new Word("Mud", "Chuodho",
                R.raw.chuodho));
        words.add(new Word("Soil/ Land/ Earth, Lands", "Lowo, Lope",
                R.raw.low));
        words.add(new Word("Hole, Holes", "Bur, Buche",
                R.raw.bur));
        words.add(new Word("Borehole/ Well, Boreholes/ Wells", "Soko, Sokni",
                R.raw.soko));
        words.add(new Word("Stone, Stones/ Rock", "Kidi, Kite/ Luanda",
                R.raw.kidi));
        words.add(new Word("Field", "Pap",
                R.raw.pap));
        words.add(new Word("Game/ Sport/ Play, Games/ Sports", "Tugo, Tuke",
                R.raw.tugo));
        words.add(new Word("Half/ Middle", "Diere",
                R.raw.diere));
        words.add(new Word("Equal", "Maromore",
                R.raw.maromore));
        words.add(new Word("Garden, Gardens", "Puodho/ Ndalo, Puothe",
                R.raw.puodho));
        words.add(new Word("Grass", "Lum",
                R.raw.lum));
        words.add(new Word("Leaf", "Oboke",
                R.raw.oboke));
        words.add(new Word("Twig, Twigs", "Kede, Kete",
                R.raw.kede));
        words.add(new Word("Forest, Bush", "Bungu",
                R.raw.bungu));
        words.add(new Word("Dew", "Tho",
                R.raw.tho));
        words.add(new Word("Wilderness", "Thim",
                R.raw.thim));
        words.add(new Word("Mountain/ Hill, Mountains/ Hills", "Got, Gode",
                R.raw.got));
        words.add(new Word("Lake, Lakes, Sea, Beach", "Nam, Nembe, Ataro, Wath",
                R.raw.namb));
        words.add(new Word("Boat", "Yie",
                R.raw.yie));
        words.add(new Word("Stream/ River (Plural)", "Aora, (Aore/ Aoche)",
                R.raw.aora));
        words.add(new Word("Island", "Chula",
                R.raw.chula));
        words.add(new Word("Garbage", "Yugi",
                R.raw.yugi));
        words.add(new Word("Market", "Chiro",
                R.raw.chiro));
        words.add(new Word("Basket", "Adita",
                R.raw.adita));
        words.add(new Word("Sound/ Noise", "Koko",
                R.raw.koko));
        words.add(new Word("Quiet/ Calm", "Ling",
                R.raw.ling));
        words.add(new Word("Voice, Voices", "Duol, Duond",
                R.raw.duol_duond));
        words.add(new Word("Virgin", "Masilili",
                R.raw.masilili));
        words.add(new Word("Intercourse", "Terruok/ Nindruok/ Ng'otho/ Haro",
                R.raw.terruok));
        words.add(new Word("Origin", "Karcharuok",
                R.raw.karchakruok));
        words.add(new Word("Immigrant", "Jabuoro",
                R.raw.jabuoro));
        words.add(new Word("Reach/ Arrive", "Chopo",
                R.raw.chopo));
        words.add(new Word("Get lost", "Lal",
                R.raw.lal));
        words.add(new Word("It's been long", "Ilal",
                R.raw.ilal));
        words.add(new Word("Rich, Rich/ Stick/ Light something", "Mewo, Moko",
                R.raw.mewo));
        words.add(new Word("Poverty", "Dhier",
                R.raw.dhier));
        words.add(new Word("Poor person", "Ng'at maodhier/ Jachan",
                R.raw.ngat_maodhier));
        words.add(new Word("Hunger", "Kech/ Denyo",
                R.raw.kech));
        words.add(new Word("Starving person", "Jakech",
                R.raw.jakech));
        words.add(new Word("Thirst", "Riyo",
                R.raw.riyo));
        words.add(new Word("Character", "Kido/ Kit",
                R.raw.kido));
        words.add(new Word("Doctor/ Medicine man, Medicine, Heal", "Laktar/ Ajuoga/ Jayath, Yath, Chango",
                R.raw.laktar));
        words.add(new Word("Treat, Treatment", "Thiedho, Thiedho",
                R.raw.thiedho));
        words.add(new Word("Teacher, Teachers", "Japuonj, Jopuonj",
                R.raw.japuonj));
        words.add(new Word("Nanny", "Japidi/ Jatich ot",
                R.raw.japidi));
        words.add(new Word("Servant/Slave, Servant", "Misumba, Jatich ot",
                R.raw.misumba_servant));
        words.add(new Word("Company", "Kambe",
                R.raw.kambe));
        words.add(new Word("Assistant, Assistants", "Jakony, Jokony",
                R.raw.jakony));
        words.add(new Word("Secretary, Secretaries", "Jagoro, Jogoro",
                R.raw.jagoro));
        words.add(new Word("Prostitute", "Jachodo",
                R.raw.chodo));
        words.add(new Word("Spy/ Ambassador", "Jambetre",
                R.raw.jambetre));
        words.add(new Word("Witness", "Janeno",
                R.raw.janeno));
        words.add(new Word("Mediator of marriage cases", "Jagam",
                R.raw.jagam));
        words.add(new Word("Farmer, Farmers", "Japur, Jopur",
                R.raw.japur));
        words.add(new Word("Shepherd/ Pastor, Pastors, Priest, Priests", "Jakuath, Jokuath, Jadolo, Jodolo",
                R.raw.jakuath));
        words.add(new Word("Prophet, Prophets", "Jahulo, Johulo",
                R.raw.jahulo));
        words.add(new Word("Miracle", "Hono",
                R.raw.hono));
        words.add(new Word("Carpenter", "Fund Mbao",
                R.raw.fundmbao));
        words.add(new Word("Tailor", "Jatweng'o",
                R.raw.jatwengo));
        words.add(new Word("Builder, Builders", "Jagedo, Jogedo",
                R.raw.jagedo));
        words.add(new Word("Cook", "Jatedo, Jotedo",
                R.raw.jatedo));
        words.add(new Word("Dancer, Dancers", "Jamiel, Jomiel",
                R.raw.jamiel));
        words.add(new Word("Herdsman, Herdsmen", "Jakwath, Jokwath",
                R.raw.jakwath));
        words.add(new Word("Warrior/ Soldier, Warriors/ Soldiers", "Jakedo/Jalweny, Jokedo/Jolweny",
                R.raw.jakedo));
        words.add(new Word("War/ Battle, Wars", "Lweny, Lwenje",
                R.raw.lweny));
        words.add(new Word("Glutton, Gluttons", "Jawuoro, Jowuoro",
                R.raw.jawuoro));
        words.add(new Word("Drunkard, Drunkards", "Jametho, Jometho",
                R.raw.jometho));
        words.add(new Word("Thief, Thieves", "Jamecho/ Jakuo, Jokuo",
                R.raw.jamecho));
        words.add(new Word("Murderer, Murderers", "Janek, Jonek",
                R.raw.janek));
        words.add(new Word("Meeting", "Bura/ Chokruok",
                R.raw.bura));
        words.add(new Word("Member, Members", "Jakanyo, Jokanyo",
                R.raw.jakanyo));
        words.add(new Word("Progress", "Dongruok",
                R.raw.dongruok));
        words.add(new Word("Discussion/ Speech, Word/ Talk", "Twak, Wach",
                R.raw.twak));
        words.add(new Word("Discussion/ Chat/ Argument/ Debate", "Mbaka",
                R.raw.mbaka));
        words.add(new Word("Tell/ Show", "Nyiso",
                R.raw.nyiso));
        words.add(new Word("Story", "Sigana",
                R.raw.sigana));
        words.add(new Word("Leader/Boss, Leaders", "Jatelo, Jotelo",
                R.raw.jatelo));
        words.add(new Word("Chairman, Chairmen", "Jakom, Jokom",
                R.raw.jakom));
        words.add(new Word("King/ Chief, Kings/ Chiefs", "Ruoth, Ruodhe",
                R.raw.ruoth));
        words.add(new Word("Judge", "Jang'ad bura",
                R.raw.jangadbura));
        words.add(new Word("Order", "Chenro",
                R.raw.chenro));
        words.add(new Word("Pardon", "Ng'uono",
                R.raw.nguono));
        words.add(new Word("Prison", "Od tuech",
                R.raw.tuech));
        words.add(new Word("Noble", "Maokebe",
                R.raw.maokebe));
        words.add(new Word("Miss, Mr. ", "Nyadendi, Migosi",
                R.raw.nyadendi));
        words.add(new Word("Law/ Norm", "Chik",
                R.raw.chik));
        words.add(new Word("President", "Ker",
                R.raw.ker));
        words.add(new Word("Vice", "Jalup",
                R.raw.jalup));
        words.add(new Word("Vote (Noun)", "Ombulu",
                R.raw.ombulu));
        words.add(new Word("Ministry", "Migawo",
                R.raw.migawo));
        words.add(new Word("Oath", "Singruok, Kuong'ruok",
                R.raw.singruok));
        words.add(new Word("God", "Nyasaye/ Were/ Ruoth/ Obong'o/ Obong'o Nyakalaga",
                R.raw.nyasaye));
        words.add(new Word("Night runner, Night runners, Satan/ Ghost", "Jajuok, Jojuogi, Jachien/ Obel",
                R.raw.jajuok));
        words.add(new Word("Ghost", "Jachien/ Koko",
                R.raw.jachien));
        words.add(new Word("Sin/ Bad", "Richo",
                R.raw.richo));
        words.add(new Word("Spoil/ Sin/ Mistake", "Ketho",
                R.raw.ketho));
        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_colors);

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
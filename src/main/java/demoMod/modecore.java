package demoMod;

import basemod.AutoAdd;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import cards.defend_mi;
import cards.hammerstrike_mi;
import cards.strike_mi;
import characters.mikan;
import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.badlogic.gdx.graphics.Color;
import basemod.BaseMod;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.localization.KeywordStrings;
import javassist.compiler.ast.Keyword;
import pathes.ThmodClassEnum;
import relics.mi_darkenergy;
import com.badlogic.gdx.graphics.Color;
import relics.mi_ghost_vessel;

import java.nio.charset.StandardCharsets;

import static characters.mikan.PlayerColorEnum.EXAMPLE_GREEN;
import static characters.mikan.PlayerColorEnum.MY_CHARACTER;
//import static characters.mikan.PlayerLibraryEnum.MY_CHARACTER;

@SpireInitializer
public class modecore implements EditCardsSubscriber, EditKeywordsSubscriber,EditStringsSubscriber,EditCharactersSubscriber,EditRelicsSubscriber {


    // 人物选择界面按钮的图片
    private static final String MY_CHARACTER_BUTTON = "mikanresources/images/character_img/Character_Button.png";
    // 人物选择界面的立绘
    private static final String MY_CHARACTER_PORTRAIT = "mikanresources/images/character_img/Character_Portrait.png";
    // 攻击牌的背景（小尺寸）
    private static final String BG_ATTACK_512 = "mikanresources/images/512/bg_attack_512.png";
    // 能力牌的背景（小尺寸）
    private static final String BG_POWER_512 = "mikanresources/images/512/bg_power_512.png";
    // 技能牌的背景（小尺寸）
    private static final String BG_SKILL_512 = "mikanresources/images/512/bg_skill_512.png";
    // 在卡牌和遗物描述中的能量图标
    private static final String SMALL_ORB = "mikanresources/images/character_img/small_orb.png";
    // 攻击牌的背景（大尺寸）
    private static final String BG_ATTACK_1024 = "mikanresources/images/1024/bg_attack.png";
    // 能力牌的背景（大尺寸）
    private static final String BG_POWER_1024 = "mikanresources/images/1024/bg_power.png";
    // 技能牌的背景（大尺寸）
    private static final String BG_SKILL_1024 = "mikanresources/images/1024/bg_skill.png";
    // 在卡牌预览界面的能量图标
    private static final String BIG_ORB = "mikanresources/images/character_img/card_orb.png";
    // 小尺寸的能量图标（战斗中，牌堆预览）
    private static final String ENEYGY_ORB = "mikanresources/images/character_img/cost_orb.png";

    public static final Color MY_COLOR = new Color(150.0F / 255.0F, 220.0F / 255.0F, 56.0F / 255.0F, 1.0F);



    public modecore() {
        BaseMod.subscribe(this);
        BaseMod.addColor(EXAMPLE_GREEN,MY_COLOR, MY_COLOR, MY_COLOR, MY_COLOR, MY_COLOR, MY_COLOR, MY_COLOR,BG_ATTACK_512,BG_SKILL_512,BG_POWER_512,ENEYGY_ORB,BG_ATTACK_1024,BG_SKILL_1024,BG_POWER_1024,BIG_ORB,SMALL_ORB);
    }

    public static void initialize() {
        new modecore();
    }

    @Override
    public void receiveEditCards() {
        //BaseMod.addCard(new strike_mi());
        new AutoAdd("MikanMod") // 这里填写你在ModTheSpire.json中写的modid
                .packageFilter(strike_mi.class) // 寻找所有和此类同一个包及内部包的类（本例子是所有卡牌）
                .setDefaultSeen(true) // 是否将卡牌标为可见
                .cards(); // 开始批量添加卡牌
    }

    @Override
    public void receiveEditKeywords() {
        BaseMod.addKeyword("mikanmod", "幽魂能量", new String[] {"幽魂能量"}, "每点 #y幽魂能量 会使 #b收割 卡牌的伤害提高 #y3 点。");
        BaseMod.addKeyword("mikanmod", "吸血", new String[] {"吸血"}, " #y未被格挡 的攻击伤害会用以恢复生命值");
    }

    public void receiveEditStrings() {
        String lang;
        if (Settings.language == Settings.GameLanguage.ZHS) {
            lang = "ZHS";
        } else {
            lang = "ENG";
        }
        BaseMod.loadCustomStringsFile(CardStrings.class, "mikanresources/localization/" + lang + "/mikanmod_card_zh.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, "mikanresources/localization/" + lang + "/mikanmod_characters_zh.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, "mikanresources/localization/" + lang + "/mikanmod_relics_zh.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, "mikanresources/localization/" + lang + "/mikanmod_power_zh.json");
    }

    @Override
    public void receiveEditCharacters() {
        // 向basemod注册人物
        BaseMod.addCharacter(new mikan(CardCrawlGame.playerName), MY_CHARACTER_BUTTON, MY_CHARACTER_PORTRAIT,MY_CHARACTER);
    }

    @Override
    public void receiveEditRelics() {
        BaseMod.addRelic(new mi_darkenergy(),RelicType.SHARED);
        BaseMod.addRelic(new mi_ghost_vessel(),RelicType.SHARED);
        // RelicType表示是所有角色都能拿到的遗物，还是一个角色的独有遗物
    }
}
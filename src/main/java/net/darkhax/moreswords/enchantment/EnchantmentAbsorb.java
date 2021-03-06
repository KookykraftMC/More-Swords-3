package net.darkhax.moreswords.enchantment;

import net.darkhax.moreswords.handler.ConfigurationHandler;
import net.darkhax.moreswords.util.Utils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class EnchantmentAbsorb extends EnchantmentBase {
    
    protected EnchantmentAbsorb(int id, int weight, String unlocalizedName, int minLevel, int maxLevel, Item item) {
        
        super(id, weight, unlocalizedName, minLevel, maxLevel, item);
    }
    
    @Override
    public void onEntityDamaged (EntityLivingBase user, Entity target, int level) {
        
        if (isValidUser(user) && Utils.percentChance(ConfigurationHandler.absorbChance)) {
            
            EntityPlayer player = (EntityPlayer) user;
            ItemStack stack = player.getHeldItem();
            int food = Utils.nextIntII(ConfigurationHandler.absorbMin, ConfigurationHandler.absorbMax);
            float saturation = (float) (ConfigurationHandler.absorbSaturation * food);
            player.getFoodStats().addStats(food, saturation);
        }
    }
    
    @Override
    public boolean isValidUser (Entity entity) {
        
        return (entity instanceof EntityPlayer && ((EntityLivingBase) entity).getHeldItem() != null && getLevel(((EntityPlayer) entity).getHeldItem()) > 0);
    }
}
package net.kaupenjoe.magnificentstaffs.util;

import net.kaupenjoe.magnificentstaffs.entity.custom.BasicMagicProjectileEntity;
import org.joml.Vector4f;

import java.util.Map;

public class MagicColorUtils {
    public static Map<BasicMagicProjectileEntity.MagicProjectileType, Vector4f> PROJECTILE_VECTOR = Map.of(
                    BasicMagicProjectileEntity.MagicProjectileType.SAPPHIRE, new Vector4f(0.2f, 0.4f, 0.7f, 0.8f),
                    BasicMagicProjectileEntity.MagicProjectileType.DIAMOND, new Vector4f(0.2f, 0.7f, 0.8f, 0.8f),
                    BasicMagicProjectileEntity.MagicProjectileType.RUBY, new Vector4f(0.9f, 0.2f, 0.2f, 0.8f),
                    BasicMagicProjectileEntity.MagicProjectileType.AMETHYST, new Vector4f(0.5f, 0.1f, 0.6f, 0.8f),
                    BasicMagicProjectileEntity.MagicProjectileType.EMERALD, new Vector4f(0.25f, 0.65f, 0.1f, 0.8f));

    public static Map<Integer, Vector4f> PROJECTILE_VECTOR_BY_ORDINAL = Map.of(
                    0, new Vector4f(0.2f, 0.4f, 0.7f, 0.8f),
                    1, new Vector4f(0.2f, 0.7f, 0.8f, 0.8f),
                    2, new Vector4f(0.9f, 0.2f, 0.2f, 0.8f),
                    3, new Vector4f(0.5f, 0.1f, 0.6f, 0.8f),
                    4, new Vector4f(0.25f, 0.65f, 0.1f, 0.8f));
}

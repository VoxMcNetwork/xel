package net.voxmc.xel.c

import net.voxmc.xel.base.Named

interface XelModule : Named


open class XelModuleBase(final override val name: String) : XelModule
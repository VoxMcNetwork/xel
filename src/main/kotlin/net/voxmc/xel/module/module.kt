package net.voxmc.xel.module

interface XelModule
{
	val name: String
}


open class XelModuleBase(final override val name: String) : XelModule
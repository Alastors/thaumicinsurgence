# Thaumic Insurgence
Thaumic Insurgence is the latest addon for thaumcraft 4 (yes I know I'm about 8 years late to the party)
   
This mod will be adding a myriad of new features, such as a few new thaumic multis, new content that just needs to exist, and likewise will be taking over
older content from mods such as kekztech (such as the thaumium jar) that didn't balance their content well and would be better within another mod.
   
Likewise, this mod will be attempting to provide support for some older bugs/features that might not be able to be fixed directly, but do have potential. 
for alternative workarounds

I'd also like to take this line to extend my thanks to the Arcana team for their lovely textures and just amazing mod in general, I'll be spending a lot
of time backporting a lot of their content, so thank you Arcana Dev Team for allowing the use of Arcana assets except the lore!
Specificially thank you to Ibanplay for the lovely textures! Thank you for the good work sir!

If you have issues with this mod, post them here: https://github.com/Alastors/thaumicinsurgence/issues
Please specify which version of my mod you're using and where you got it please!!! <3
(And if you're reporting an issue and coming from GTNH, please specify that as well!)

Curseforge: https://www.curseforge.com/minecraft/mc-mods/thaumic-insurgence

Modrinth: https://modrinth.com/mod/thaumic_insurgence

Original Branch: https://github.com/Alastors/thaumicinsurgence
   - (Sometimes this branch might have a newer update than it might be here, though obviously sometimes this branch might be newer)

# Ver 0.0.5 - Release Saturday September 3rd, 12022/2022
    
    1. What's been updated?
      - Updated the GTNH release of the mod to use semver versioning schemes, 
        the mainline release will still use the 0.0.0.0 formatting

# Ver 0.0.0.4 - Release Saturday September 3rd, 12022/2022
    
    1. What's been updated?
      - The intercepter's entry in the thaumonomicon.

# Ver 0.0.0.2 - Release Wednesday August 31st, 12022/2022
(Yes, I know this should technically be version 1.0.0.2, but I decided last second to change how I label versions)
    
    1. What's been updated?
      - Infusion Interceptor
         - The Infusion Intercepter now has an accessible crafting recipe and I've updated it's entry in the thaumonimicon!
         
      - Thaumic Insurgence's research tab background!
         -  Thaumic Insurgence now has its own custom background for its research tab! 
         -  Thanks once again to the Arcana Dev Team for opening your beautiful textures for free use!!!
         -  DM me if you are interested on where to find/obtain Arcana's textures!
         
      - A massive overhaul in the cleanup of my code!!! 
         -  Special thanks to @kuba6000 for helping, your work is very appreciated!!

# Ver 0.0.0.1 - Release Saturday August 13th, 12022/2022
(Previously labeled as Ver 1.0.0.0, I changed how I labeled versions as of August 31st, 12022/2022)
    
    1. What is currently in the mod
    
      - Infusion Interceptor
         - Affectionately known as the "InfusionFucker" within the code, this TileEntity takes 
           advantage of a hole in thaumcraft's infusion matrix's
           code that allows me to grab hold of the AspectList of the infusion recipe, 
           which allows me to manipulate the matrix from another block.
      
           The purpose of this is to allow you to be able to interact with the infusion matrix 
           through an interfacing block (that goes directly under the
           central pedestal) to be able to use the essentia pipe system 
           (or essentia provider if you have thaumic energistics) by placing an
           IEsssentiaTransport implementing TE (such as the essentia pipes or the essentia provider) 
           directly underneath the infusion interceptor.
           
           This will allow users of the GTNH pack to get around the massive issues 
           lagwise that come with world accelerating the infusion matrix.
           The Interceptor functionally has infinite essentia throughput and is 
           specifically designed to be cheap af to encourage people to use it
           over a world accelerator, do ***not*** make it expensive or it will take away a main benefit
           
         - It currently doesn't have a crafting recipe though, this is intentional, 
           I have a few small tweaks I still need to make to it first.
    
    2. Future Plans
    
      - Taking over Kekztech's thaum stuff
        - All of kekztech's thaum additions are currently horrifically balanced, 
          (such as the thaumium jar having the same capacity as an essentia
          reservoir, but only for a single aspect, however costing titanium
          for some idiotic reason) and as such, I'm going to be pulling the
          code into this mod and handling it here.
      
      - Possibly moving my MagicBees updates to this mod instead
      
      - Introduction of new thaumic multis, such as a multi portal system to allow for an 
        alternative to rockets and likely the system that will craft magic circuits
      
      - I need to update the background to be its own instead of the current one.
         - Finished as of August 31st
      
      - Dunno fam.
      
      
      

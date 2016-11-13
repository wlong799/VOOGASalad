* Goal: Make a game authoring environment for a 2-d side scrolling platform game
* Flexibility: Level linking, character properties, winning condition, physics settings - support any type of side scrolling game, although not possible to support the most customized games
* Game data is open to both Authoring Environment, which allows users to edit the game, and Game Player & Engine, which use the game information to actually run the game. Authoring Environment and Game Player & Engine are independent of each other.
* Genre: Side scrolling platform game
* Moving background - ability to move if you move to the end of the screen, character always in the center area of the screen, background should be able to move both forwards and backwards
* Physics as an option, like non-physical, or affected by gravity and friction (can be different for different sprites)
* Characters can have potential ability of jumping, climbing, running, walking and interacting each other (e.g. monsters do damage to the player).
* Authoring Environment lets the user to create/modify the game (level, goal, sprites etc), and these information will be stored within a Game object. Game engine use the information to calculate each frame of the game under the hood,  which will be then provided for a game player to run/render the calculated frames.

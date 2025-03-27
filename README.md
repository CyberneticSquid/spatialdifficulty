# Spatial Difficulty.
Solve three birds with one stone! Give the world an inherent choice of risk and reward, give players a reason to stick together, and prevent new players from joining and immediately dying to a skeleton with an enchanted bow.

### Summary
This mod repurposes the vanilla 'local difficulty' mechanic to be determined by distance from spawn instead of time in a chunk (capped at the same values as vanilla). The mechanic is otherwise identical--causing mobs to gain more armor and weapons, have more enchantments, and special versions (i.e. potion spiders).

### Config
The rate of difficulty growth can be edited in config, and the center will always be worldspawn. The formula used is shown below.

$$\max\left(\textit{dMin}, \textit{dMax} - (\textit{dMax} - \textit{dMin})\left(\frac{a}{x^{\frac{1}{b}}}\right)\right)$$

Here, *x* is the distance from worldspawn, *dMin* and *dMax* are based on the world difficulty, and *a* and *b* are controlled by `numerator` and `exponent` respectively in the config.

### Compatibility
There should be minimal performance impact. Will have incompatibilities with any mod that changes how local difficulty works, but that should be basically none of them.
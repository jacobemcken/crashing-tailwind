# Crashing Tailwind

Trying to reproduce the following `FATAL ERROR` in a bigger project using
Tailwind:

> FATAL ERROR: Reached heap limit Allocation failed - JavaScript heap out of memory

I decided to create a project with minimal setup. While I was unable to actually
make the application crash, it is very clear that something weird is going on.
The Tailwind-process consume around 1.5-2 GB memory and makes the CPU sweat for
around 20 seconds on my laptop before it calms down.

All this for nothing... or almost nothing (generating a 9.3K CSS file).


## Reproduce the problem

But first dependencies needs to be installed:
```
npm install
```

To reproduce (the following steps can be repeated):
```bash
npm run clean
mkdir -p public/js/cljs-runtime/
npm run css:watch
```

From a different terminal, run:
```bash
npm run app:watch
```

Now follow the processes (e.g. by using `top` from a third terminal window).


Notice how the `node` process (from `npm run css:watch`) starts consuming
resources as soon as the Shadow-cljs build has finished, showing something like:
```
...
[:app] Build completed. (146 files, 0 compiled, 0 warnings, 3,90s)
```


## Why bother

Without the existence of the `public/js/cljs-runtime/` directory, the problem
doesn't seem occur.

The reason for creating the directory is to improve the developer experience of the initial setup. Without the directory Tailwind doesn't seem to be able to attach "watchers" for file changes causing the app start without CSS (until restarting `npm run css:watch`.

In a real setup both `css:watch` and `app:watch` is started at the same time in
parallel with something like `run-p`.
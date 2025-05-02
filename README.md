"E:\Tushar Khade\Softwares\Node-v22.15.0-win-x64\npm.cmd" run dev

> bs_revamp@0.0.0 dev
> vite

F:\Projects\BSA Projects\BS_Revamp\node_modules\rollup\dist\native.js:64
                throw new Error(
                      ^

Error: Cannot find module @rollup/rollup-win32-x64-msvc. npm has a bug related to optional dependencies (https://github.com/npm/cli/issues/4828). Please try `npm i` again after removing both package-lock.json and node_modules directory.
    at requireWithFriendlyError (F:\Projects\BSA Projects\BS_Revamp\node_modules\rollup\dist\native.js:64:9)
    at Object.<anonymous> (F:\Projects\BSA Projects\BS_Revamp\node_modules\rollup\dist\native.js:73:76)
    at Module._compile (node:internal/modules/cjs/loader:1730:14)
    at Object..js (node:internal/modules/cjs/loader:1895:10)
    at Module.load (node:internal/modules/cjs/loader:1465:32)
    ... 2 lines matching cause stack trace ...
    at wrapModuleLoad (node:internal/modules/cjs/loader:235:24)
    at cjsLoader (node:internal/modules/esm/translators:266:5)
    at ModuleWrap.<anonymous> (node:internal/modules/esm/translators:200:7) {
  [cause]: Error: Access is denied.
  \\?\F:\Projects\BSA Projects\BS_Revamp\node_modules\@rollup\rollup-win32-x64-msvc\rollup.win32-x64-msvc.node
      at Object..node (node:internal/modules/cjs/loader:1921:18)
      at Module.load (node:internal/modules/cjs/loader:1465:32)
      at Function._load (node:internal/modules/cjs/loader:1282:12)
      at TracingChannel.traceSync (node:diagnostics_channel:322:14)
      at wrapModuleLoad (node:internal/modules/cjs/loader:235:24)
      at Module.require (node:internal/modules/cjs/loader:1487:12)
      at require (node:internal/modules/helpers:135:16)
      at requireWithFriendlyError (F:\Projects\BSA Projects\BS_Revamp\node_modules\rollup\dist\native.js:46:10)
      at Object.<anonymous> (F:\Projects\BSA Projects\BS_Revamp\node_modules\rollup\dist\native.js:73:76)
      at Module._compile (node:internal/modules/cjs/loader:1730:14) {
    code: 'ERR_DLOPEN_FAILED'
  }
}

Node.js v22.15.0

Process finished with exit code 1



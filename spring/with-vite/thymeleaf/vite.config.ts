// 参考: https://ja.vite.dev/config/
import type { UserConfig } from "vite";
import { resolve } from "path";
import { promises as fs } from "fs";

export default {
  build: {
    minify: false,
    rollupOptions: {
      // resouces/templates に入れる html ファイルを列挙する
      input: {
        index: resolve(__dirname, "templates/index.html"),
      },
      // 出力ファイル名にhashを付けないようにする設定
      // https://github.com/vitejs/vite/issues/378#issuecomment-789366197
      output: {
        entryFileNames: `vite/[name].js`,
        chunkFileNames: `vite/[name].js`,
        assetFileNames: `vite/[name].[ext]`,
      },
    },
  },
  // plugins: [
  //   {
  //     // 全部outDir(resources/static)に入ってしまうので、templatesはresources/templatesに移動する
  //     name: "move-templates",
  //     closeBundle: async () => {
  //       const srcDir = resolve(
  //         __dirname,
  //         "../src/main/resources/static/templates"
  //       );
  //       const destDir = resolve(__dirname, "../src/main/resources/templates");

  //       try {
  //         await fs.mkdir(destDir, { recursive: true });
  //         await fs.cp(srcDir, destDir, { recursive: true, force: true });
  //         await fs.rm(srcDir, { recursive: true, force: true });
  //         console.log(`Moved templates from ${srcDir} to ${destDir}`);
  //       } catch (err) {
  //         console.error(`Failed to move templates: ${err}`);
  //       }
  //     },
  //   },
  // ],
} satisfies UserConfig;

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
        // ここに thymeleaf テンプレートファイルを列挙する
        // "hello/index": "templates/hello/index.html",
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
  plugins: [
    // ビルドしたファイルを Spring Boot のリソースディレクトリーに移す
    {
      name: "move-resources",
      closeBundle: async () => {
        const srcTemplates = resolve(__dirname, "dist/templates");
        const destTemplates = resolve(
          __dirname,
          "../src/main/resources/templates"
        );

        const srcVite = resolve(__dirname, "dist/vite");
        const destVite = resolve(
          __dirname,
          "../src/main/resources/static/vite"
        );

        try {
          await fs.rm(destTemplates, { recursive: true });
          await fs.mkdir(destTemplates, { recursive: true });
          await fs.cp(srcTemplates, destTemplates, {
            recursive: true,
            force: true,
          });
          console.log(
            `Moved templates from ${srcTemplates} to ${destTemplates}`
          );

          await fs.rm(destVite, { recursive: true });
          await fs.mkdir(destVite, { recursive: true });
          await fs.cp(srcVite, destVite, {
            recursive: true,
            force: true,
          });
          console.log(`Moved templates from ${srcVite} to ${destVite}`);
        } catch (err) {
          console.error(`Failed to move templates: ${err}`);
        }
      },
    },
  ],
} satisfies UserConfig;
